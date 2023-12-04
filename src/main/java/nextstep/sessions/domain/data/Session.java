package nextstep.sessions.domain.data;

import java.util.List;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.vo.*;
import nextstep.users.domain.NsUser;

public class Session {

    private final SessionInfo sessionInfo;
    private Registrations registrations;

    public Session(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public Session(SessionInfo sessionInfo, List<Registration> registrations) {
        this(sessionInfo, new Registrations(registrations));
    }

    public Session(SessionInfo sessionInfo, Registrations registrations) {
        this.sessionInfo = sessionInfo;
        this.registrations = registrations;
    }

    public Registration registration(NsUser user, Payment payment) {
        sessionInfo.validateEnrollment(registrations.size(), payment);
        registrations.validateDuplicateEnrollment(user);
        return new Registration(this, user, payment);
    }

    public SessionInfo sessionInfo() {
        return sessionInfo;
    }
}