package javatest.test.entity.application;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    int countByLectureId(Long lectureId);

    List<Application> findByLectureId(Long lectureId);

    Application findByLectureIdAndMemberId(Long lectureId, Long MemberId);

    void deleteAllByApplicationStatus(ApplicationStatus applicationStatus);

    void deleteByMemberIdAndLectureId(Long memberId, Long lectureId);

}
