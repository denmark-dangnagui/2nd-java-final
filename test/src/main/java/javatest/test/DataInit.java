package javatest.test;

import javatest.test.entity.lecture.Lecture;
import javatest.test.entity.lecture.LectureRepository;
import javatest.test.entity.member.Member;
import javatest.test.entity.member.MemberRepository;
import javatest.test.entity.member.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInit {


    private final LectureRepository lectureRepository;

    private final MemberRepository memberRepository;

    @PostConstruct
    void init() {
        Member lee = new Member();
        lee.setStudentName("이태민");
        lee.setAge(24);
        lee.setDepartment("경영학과");
        lee.setSubDepartment("컴퓨터공학과");
        lee.setTotalCredit(21);

        lee.setEmail("aaa1234@naver.com");
        lee.setPassword("aaa1234");
        lee.setRole(Role.STUDENT);
        lee.setMemberNumber(17010123);
        memberRepository.save(lee);

        Member hong = new Member();
        hong.setStudentName("홍석주");
        hong.setAge(22);
        hong.setDepartment("보안학과");
        hong.setSubDepartment("경영학과");
        hong.setTotalCredit(21);

        hong.setEmail("somefood@naver.com");
        hong.setPassword("somefood1234");
        hong.setRole(Role.STUDENT);
        hong.setMemberNumber(16010123);
        memberRepository.save(hong);

        Member jung = new Member();
        jung.setStudentName("정상윤");
        jung.setAge(32);
        jung.setDepartment("해양학과");
        jung.setSubDepartment("보안학과");
        jung.setTotalCredit(21);

        jung.setEmail("jsy53@naver.com");
        jung.setPassword("jsy53");
        jung.setRole(Role.STUDENT);
        jung.setMemberNumber(12010123);
        memberRepository.save(jung);


        Member ron = new Member();
        ron.setStudentName("호날두");
        ron.setAge(32);
        ron.setDepartment("해양학과");
        ron.setSubDepartment("none");
        ron.setTotalCredit(21);

        ron.setEmail("ronaldo@naver.com");
        ron.setPassword("ronaldo");
        ron.setRole(Role.STUDENT);
        ron.setMemberNumber(10010123);
        memberRepository.save(ron);

        Member son = new Member();
        son.setStudentName("손흥민");
        son.setAge(32);
        son.setDepartment("축구학과");
        son.setSubDepartment("none");
        son.setTotalCredit(21);

        son.setEmail("son@naver.com");
        son.setPassword("son");
        son.setRole(Role.STUDENT);
        son.setMemberNumber(13010123);
        memberRepository.save(son);

        Member kim = new Member();
        kim.setStudentName("김흥국");
        kim.setAge(32);
        kim.setDepartment("호랑나비");
        kim.setSubDepartment("none");
        kim.setTotalCredit(21);

        kim.setEmail("kim@naver.com");
        kim.setPassword("kim");
        kim.setRole(Role.STUDENT);
        kim.setMemberNumber(13010123);
        memberRepository.save(kim);

        Lecture one = new Lecture();
        one.setProfessor("교수1");
        one.setTotalStudent(5);
        one.setTakeStudent(0);
        one.setLectureName("경영학");
        one.setDepartment("경영학과");
        one.setCredit(3);
        one.setDay("월");
        one.setStartTime(LocalTime.MIDNIGHT.plusHours(9));
        one.setEndTime(LocalTime.MIDNIGHT.plusHours(12));
        lectureRepository.save(one);

        Lecture one_1 = new Lecture();
        one_1.setProfessor("교수2");
        one_1.setTotalStudent(5);
        one_1.setTakeStudent(0);
        one_1.setLectureName("인적관리");
        one_1.setDepartment("경영학과");
        one_1.setCredit(3);
        one_1.setDay("화");
        one_1.setStartTime(LocalTime.MIDNIGHT.plusHours(9));
        one_1.setEndTime(LocalTime.MIDNIGHT.plusHours(12));
        lectureRepository.save(one_1);

        Lecture one_2 = new Lecture();
        one_2.setProfessor("교수3");
        one_2.setTotalStudent(5);
        one_2.setTakeStudent(0);
        one_2.setLectureName("마케팅");
        one_2.setDepartment("경영학과");
        one_2.setCredit(3);
        one_2.setDay("월");
        one_2.setStartTime(LocalTime.MIDNIGHT.plusHours(9));
        one_2.setEndTime(LocalTime.MIDNIGHT.plusHours(12));
        lectureRepository.save(one_2);

        Lecture two = new Lecture();
        two.setProfessor("교수1");
        two.setTotalStudent(5);
        two.setTakeStudent(0);
        two.setCredit(2);
        two.setDay("화");
        two.setLectureName("해양학");
        two.setDepartment("해양학과");
        two.setStartTime(LocalTime.MIDNIGHT.plusHours(10));
        two.setEndTime(LocalTime.MIDNIGHT.plusHours(13));
        lectureRepository.save(two);

        Lecture three = new Lecture();
        three.setProfessor("교수2");
        three.setTotalStudent(5);
        three.setTakeStudent(0);
        three.setCredit(3);
        three.setDay("수");
        three.setLectureName("보안학");
        three.setDepartment("보안학과");
        three.setStartTime(LocalTime.MIDNIGHT.plusHours(16));
        three.setEndTime(LocalTime.MIDNIGHT.plusHours(18));
        lectureRepository.save(three);

        Lecture four = new Lecture();
        four.setProfessor("교수3");
        four.setTotalStudent(5);
        four.setTakeStudent(0);
        four.setLectureName("교양학");
        four.setDepartment("교양학과");
        four.setDay("월");
        four.setCredit(3);
        four.setStartTime(LocalTime.MIDNIGHT.plusHours(20));
        four.setEndTime(LocalTime.MIDNIGHT.plusHours(22));
        lectureRepository.save(four);

        Lecture five = new Lecture();
        five.setProfessor("교수1");
        five.setTotalStudent(5);
        five.setTakeStudent(0);
        five.setLectureName("디자인패턴");
        five.setDepartment("컴퓨터공학과");
        five.setDay("월");
        five.setCredit(3);
        five.setStartTime(LocalTime.MIDNIGHT.plusHours(20));
        five.setEndTime(LocalTime.MIDNIGHT.plusHours(22));
        lectureRepository.save(five);

        Lecture six = new Lecture();
        six.setProfessor("교수2");
        six.setTotalStudent(5);
        six.setTakeStudent(0);
        six.setLectureName("자료구조");
        six.setDepartment("컴퓨터공학과");
        six.setDay("화");
        six.setCredit(3);
        six.setStartTime(LocalTime.MIDNIGHT.plusHours(1));
        six.setEndTime(LocalTime.MIDNIGHT.plusHours(2));
        lectureRepository.save(six);

        Lecture seven = new Lecture();
        seven.setProfessor("교수3");
        seven.setTotalStudent(5);
        seven.setTakeStudent(0);
        seven.setLectureName("알고리즘");
        seven.setDepartment("컴퓨터공학과");
        seven.setDay("월");
        seven.setCredit(3);
        seven.setStartTime(LocalTime.MIDNIGHT.plusHours(2));
        seven.setEndTime(LocalTime.MIDNIGHT.plusHours(4));
        lectureRepository.save(seven);


        Lecture eight = new Lecture();
        eight.setProfessor("교수1");
        eight.setTotalStudent(5);
        eight.setTakeStudent(0);
        eight.setLectureName("운영체제");
        eight.setDepartment("컴퓨터공학과");
        eight.setDay("수");
        eight.setCredit(3);
        eight.setStartTime(LocalTime.MIDNIGHT.plusHours(7));
        eight.setEndTime(LocalTime.MIDNIGHT.plusHours(8));
        lectureRepository.save(eight);

        Lecture nine = new Lecture();
        nine.setProfessor("교수1");
        nine.setTotalStudent(5);
        nine.setTakeStudent(0);
        nine.setLectureName("축구");
        nine.setDepartment("축구학과");
        nine.setDay("금");
        nine.setCredit(3);
        nine.setStartTime(LocalTime.MIDNIGHT.plusHours(6));
        nine.setEndTime(LocalTime.MIDNIGHT.plusHours(7));
        lectureRepository.save(nine);

        Lecture ten = new Lecture();
        ten.setProfessor("교수1");
        ten.setTotalStudent(5);
        ten.setTakeStudent(0);
        ten.setLectureName("운영체제");
        ten.setDepartment("컴퓨터공학과");
        ten.setDay("목");
        ten.setCredit(3);
        ten.setStartTime(LocalTime.MIDNIGHT.plusHours(15));
        ten.setEndTime(LocalTime.MIDNIGHT.plusHours(17));
        lectureRepository.save(ten);

    }
}
