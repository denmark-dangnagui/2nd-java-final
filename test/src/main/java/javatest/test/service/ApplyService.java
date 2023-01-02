package javatest.test.service;

import javatest.test.dto.ApplyDto;
import javatest.test.dto.ProfessorLectureDto;
import javatest.test.entity.application.Application;
import javatest.test.entity.application.ApplicationRepository;
import javatest.test.entity.application.ApplicationStatus;
import javatest.test.entity.lecture.Lecture;
import javatest.test.entity.lecture.LectureRepository;
import javatest.test.entity.member.Member;
import javatest.test.entity.member.MemberRepository;
import javatest.test.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ApplyService {

    private final LectureRepository lectureRepository;
    private final MemberRepository memberRepository;
    private final ApplicationRepository applicationRepository;

    @Transactional
    public void applyLecture(ApplyDto applyDto) {
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 11, 2, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 11, 6, 0, 0);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            Lecture lecture = lectureRepository.findById(applyDto.getLectureId()).orElseThrow();
            Member member = memberRepository.findById(applyDto.getStudentId()).orElseThrow();
            List<Long> applicationList = member.getApplicationList().stream().map(Application::getLecture).map(Lecture::getId).collect(Collectors.toList());
            if (((member.getTakeCredit() == member.getTotalCredit()) || ((member.getTakeCredit() + lecture.getCredit()) > member.getTotalCredit()))) {
                throw new OverCreditException("신청할 수 있는 학점을 다 채웠습니다!");
            } else if (lecture.getTotalStudent() == lecture.getTakeStudent()) {
                throw new AlreadyFullException("수강 인원이 다 찼습니다!");
            } else if (!isDupliacteTime(member, lecture)) {
                throw new DuplicateTimeException("동일한 시간대에 수강하는 수업이 있습니다!");
            } else if (applicationList.contains(lecture.getId())) {
                Application app = applicationRepository.findByLectureIdAndMemberId(lecture.getId(), member.getId());
                if ((app.getLecture().getId() == lecture.getId()) && app.getApplicationStatus().equals(ApplicationStatus.ZZIM)) {
                    app.setApplicationStatus(ApplicationStatus.DONE);
                    lecture.setTakeStudent(lecture.getTakeStudent() + 1);
                    member.setTakeCredit(member.getTakeCredit() + lecture.getCredit());
                }
            } else {
                Application application = new Application();
                lecture.setTakeStudent(lecture.getTakeStudent() + 1);
                member.setTakeCredit(member.getTakeCredit() + lecture.getCredit());
                application.setApplicationStatus(ApplicationStatus.DONE);
                application.setLecture(lecture);
                application.setMember(member);
                applicationRepository.save(application);
            }
        } else {
            throw new NotYetException("아직 수강신청 기간이 아닙니다!");
        }
    }


    @Transactional
    public void withdrawLecture(Long memberId,Long lectureId) {
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 11, 2, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 11, 6, 0, 0);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            applicationRepository.deleteByMemberIdAndLectureId(memberId, lectureId);
        }
        else {
            throw new NotYetException("아직 수강 신청 기간이 아닙니다.");
        }
    }

    @Transactional
    public void deleteApplication() {
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 11, 2, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 11, 6, 0, 0);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            applicationRepository.deleteAllByApplicationStatus(ApplicationStatus.ZZIM);
        }
        else {
            throw new NotYetException("아직 수강 신청 기간이 아닙니다.");
        }
    }


    public boolean isDupliacteTime(Member member, Lecture lecture) {
        List<Application> takeLectures = member.getApplicationList();
        String day = lecture.getDay();
        LocalTime lStarts = lecture.getStartTime();
        LocalTime lEnd = lecture.getEndTime();
        for (Application lesson : takeLectures) {
            // 수강시간이 조금이라도 겹치는 것을 방지하는 로직
            if (lesson.getApplicationStatus().equals(ApplicationStatus.DONE)) {
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
        }
        return true;
    }
}
