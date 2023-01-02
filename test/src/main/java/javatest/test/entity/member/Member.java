package javatest.test.entity.member;

import javatest.test.entity.application.Application;
import javatest.test.entity.lecture.Lecture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private Role role;

    private int memberNumber;

    private String studentName;

    private int age;

    private String department;

    private String subDepartment;

    private int totalCredit; // 들을 수 있는 총학점

    private int takeCredit;

    @OneToMany(mappedBy = "member")
    private List<Application> applicationList = new ArrayList<>();


    @ManyToOne
    private Lecture lecture;

}
