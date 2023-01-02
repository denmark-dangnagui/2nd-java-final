package javatest.test.entity.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationStatus {
    BASKET("장바구니"),
    DONE("신청완료"),
    ZZIM("찜");
    private String korean;
}
