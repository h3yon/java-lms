package nextstep.courses.domain.strategy;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.exception.IncorrectAmountException;
import nextstep.courses.exception.SessionFullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidEnrollmentStrategyTest {

    @Test
    @DisplayName("유료 강의 최대 인원까지 차오른 강의를 신청하면 예외 처리 한다")
    void enroll() {
        PaidEnrollmentStrategy paidEnrollmentStrategy = new PaidEnrollmentStrategy(0L, 0L, 0, new Amount(10000L));

        assertThrows(SessionFullException.class, () -> paidEnrollmentStrategy.enroll(10000L, new Student(), new Students()),
                "수강 신청 인원이 마감 되었습니다.");
    }

    @Test
    @DisplayName("결제 금액과 강의 금액이 다르면 예외 처리 한다")
    void enroll2() {
        Amount amount = new Amount(20000L);
        PaidEnrollmentStrategy paidEnrollmentStrategy = new PaidEnrollmentStrategy(0L, 0L, 1, amount);

        assertThrows(IncorrectAmountException.class, () -> paidEnrollmentStrategy.enroll(15000L, new Student(), new Students()),
                "결제 금액과 강의 금액이 다릅니다.");
    }

}