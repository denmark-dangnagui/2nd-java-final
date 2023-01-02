package javatest.test.service;

import javatest.test.dto.ProfessorLectureDto;
import javatest.test.entity.application.Application;
import javatest.test.entity.application.ApplicationRepository;
import javatest.test.entity.application.ApplicationStatus;
import javatest.test.entity.lecture.Lecture;
import javatest.test.entity.lecture.LectureRepository;
import javatest.test.entity.member.Member;
import javatest.test.exception.NotYetException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfessorService {

    private final LectureRepository lectureRepository;

    private final ApplicationRepository applicationRepository;
    public List<ProfessorLectureDto> professorLecture(String professor) {
        LocalDateTime date1 = LocalDateTime.of(2023, 1, 11, 2, 0, 0);
        LocalDateTime date2 = LocalDateTime.of(2023, 1, 11, 6, 0, 0);
        if (LocalDateTime.now().isAfter(date1) && LocalDateTime.now().isBefore(date2)) {
            List<ProfessorLectureDto> dto = new ArrayList<>();
            List<Lecture> lectures = lectureRepository.findByProfessor(professor);
            for (Lecture lecture : lectures) {
                List<String> names = applicationRepository.findByLectureId(lecture.getId())
                        .stream()
                        .filter(a -> a.getApplicationStatus() == ApplicationStatus.DONE)
                        .map(Application::getMember)
                        .map(Member::getStudentName)
                        .collect(Collectors.toList());
                ProfessorLectureDto professorLectureDto = new ProfessorLectureDto();
                professorLectureDto.setLectureName(lecture.getLectureName());
                professorLectureDto.setMemberName(names);
                professorLectureDto.setTakeLecture(lecture.getTakeStudent());
                dto.add(professorLectureDto);
            }
            return dto;
        }else {
            throw new NotYetException("아직 수강 신청 기간이 아닙니다!");
        }

    }
}
