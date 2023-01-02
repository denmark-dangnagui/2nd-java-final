package javatest.test.service;

import javatest.test.dto.ApplyDto;
import javatest.test.entity.application.Application;
import javatest.test.entity.application.ApplicationRepository;
import javatest.test.entity.application.ApplicationStatus;
import javatest.test.entity.lecture.Lecture;
import javatest.test.entity.lecture.LectureRepository;
import javatest.test.entity.member.Member;
import javatest.test.entity.member.MemberRepository;
import javatest.test.exception.DuplicateTimeException;
import javatest.test.exception.NotYetException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BasketService {

    private final ApplicationRepository applicationRepository;

    private final LectureRepository lectureRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void postTempBasket(ApplyDto applyDto) {
        //장바구니 기간에만 이 api를 사용할 수 있게 해뒀습니다.
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 9, 2, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 10, 6, 0, 0);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            Application application = new Application();
            Lecture lecture = lectureRepository.findById(applyDto.getLectureId()).orElseThrow();
            Member member = memberRepository.findById(applyDto.getStudentId()).orElseThrow();
            boolean dupliacteTime = isDupliacteTime(member, lecture);
            if (!dupliacteTime) {
                throw new DuplicateTimeException("동일한 시간대에 수강하고자 하는 과목이 있습니다!");
            }
            application.setLecture(lecture);
            application.setMember(member);
            application.setApplicationStatus(ApplicationStatus.BASKET);
            applicationRepository.save(application);
        } else {
            throw new NotYetException("아직 수강 신청 기간이 아닙니다!");
        }
    }

    @Transactional
    public void postBasketRealLecture() {
        //장바구니 기간 후에 이 api를 호출할 수 있게 해두었습니다.
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 10, 6, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 11, 1, 59, 59);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            List<Application> all = applicationRepository.findAll();
            for (Application a : all) {
                int i = applicationRepository.countByLectureId(a.getLecture().getId());
                if (i <= 5) {
                    Lecture lecture = lectureRepository.findById(a.getLecture().getId()).orElseThrow();
                    lecture.setTakeStudent(i);
                    a.getMember().setTakeCredit(a.getLecture().getCredit());
                    a.setApplicationStatus(ApplicationStatus.DONE);
                } else {
                    a.setApplicationStatus(ApplicationStatus.ZZIM);
                }
            }
        }
        else {
            throw new NotYetException("아직 수강신청 기간이 아닙니다.");
        }
    }


    public boolean isDupliacteTime(Member member, Lecture lecture) {
        List<Application> takeLectures = member.getApplicationList();
        String day = lecture.getDay();
        LocalTime lStarts = lecture.getStartTime();
        LocalTime lEnd = lecture.getEndTime();
        for (Application lesson : takeLectures) {
            // 수강시간이 조금이라도 겹치는 것을 방지하는 로직
            if (day.equals(lesson.getLecture().getDay())) {
                if ((lStarts.isAfter(lesson.getLecture().getStartTime()) && lEnd.isBefore(lesson.getLecture().getEndTime())) ||
                        ((lStarts.isAfter(lesson.getLecture().getStartTime()) && lStarts.isBefore(lesson.getLecture().getEndTime())) && lEnd.isAfter(lesson.getLecture().getEndTime())) ||
                        (lStarts.isBefore(lesson.getLecture().getStartTime()) && (lEnd.isAfter(lesson.getLecture().getStartTime()) && lEnd.isBefore(lesson.getLecture().getEndTime()))) ||
                        (lStarts.isBefore(lesson.getLecture().getStartTime()) && lEnd.isAfter(lesson.getLecture().getEndTime())) ||
                        (lStarts.equals(lesson.getLecture().getStartTime()) && (lEnd.equals(lesson.getLecture().getEndTime())))) {
                    return false;
                }
            }
        }
        return true;
    }
}
