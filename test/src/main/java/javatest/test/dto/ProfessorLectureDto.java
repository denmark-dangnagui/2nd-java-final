package javatest.test.dto;

import javatest.test.entity.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProfessorLectureDto {

    private List<String> memberName = new ArrayList<>();

    private int takeLecture;

    private String lectureName;

}
