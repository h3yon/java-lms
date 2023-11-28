package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionTest {

    private static final LocalDateTime START_DATE = LocalDateTime.of(2020, 1, 1, 0, 0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2020, 2, 1, 0, 0);
    private final Session defaultSession = new Session(
            START_DATE,
            END_DATE,
            SessionType.PAID,
            new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
            SessionStatus.OPENED,
            10,
            1000
    );

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void sessionHasStartAndEndDate() {
        Session session = SESSION_모집중_10명_1000원();
        assertThat(session)
                .hasFieldOrProperty("startDate")
                .hasFieldOrProperty("endDate");

    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void sessionHasCoverUrl() {
        assertThat(defaultSession).hasFieldOrProperty("coverImage");
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void sessionHasSessionType() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Session(
                    START_DATE,
                    END_DATE,
                    SessionType.FREE,
                    new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
                    SessionStatus.OPENED,
                    1,
                    1000
            );
        });
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void sessionHasFee() {
        Session session = SESSION_모집중_10명_1000원();
        assertThat(session.enroll(NsUserTest.JAVAJIGI, 1000)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void sessionHasMaximumEnrollment() {
        Session session = SESSION_최대인원으로_수강신청됨();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            session.enroll(NsUserTest.SANJIGI, 1000);
        });
    }

    private static Session SESSION_최대인원으로_수강신청됨() {
        Session session = SESSION_모집중_10명_1000원();
        for (int i = 0; i < 10; i++) {
            session.enroll(NsUserTest.JAVAJIGI, 1000);
        }
        return session;
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void sessionHasSessionStatus() {
        Session session = SESSION_모집중_10명_1000원();
        assertThat(session.enroll(NsUserTest.JAVAJIGI, 1000)).isTrue();
    }

    private static Session SESSION_모집중_10명_1000원() {
        Session session = new Session(
                START_DATE,
                END_DATE,
                SessionType.PAID,
                new Image(ImageTest.IMAGE_URL, 1024, 1024, ImageType.PNG),
                SessionStatus.OPENED,
                10,
                1000
        );
        return session;
    }

}
