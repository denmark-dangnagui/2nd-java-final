package javatest.test.entity.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    STUDENT("학생"),
    PROFESSOR("교수");

    private String korean;
}
