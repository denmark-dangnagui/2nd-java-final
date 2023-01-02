package javatest.test.entity.application;

import javatest.test.entity.lecture.Lecture;
import javatest.test.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Lecture lecture;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Builder
    public Application(Member member, Lecture lecture, ApplicationStatus applicationStatus) {
        this.member = member;
        this.lecture = lecture;
        this.applicationStatus = applicationStatus;
    }
}
