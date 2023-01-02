package javatest.test.entity.lecture;

import com.fasterxml.jackson.annotation.JsonFormat;
import javatest.test.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lectureName;

    private String professor;

    private String day;// 요일
    @JsonFormat(pattern = "hh:mm:ss", timezone = "Asia/Seoul")
    private LocalTime startTime;

    @JsonFormat(pattern = "hh:mm:ss", timezone = "Asia/Seoul")
    private LocalTime endTime;
    private int totalStudent;

    private int takeStudent;

    private int credit;

    private String department;

    @OneToMany
    private List<Member> member = new ArrayList<>();




    @Builder
    public Lecture(Long id, String lectureName, int totalStudent, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.lectureName = lectureName;
        this.totalStudent = totalStudent;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
