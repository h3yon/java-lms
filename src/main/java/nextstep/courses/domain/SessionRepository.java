package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface SessionRepository {
    long save(Session session);

    Session findById(Long id);

    void saveSessionUser(Session session);

    List<SessionUser> findAllBySessionId(Long sessionId);

    List<SessionUser> findAllBySessionIdAndUserIds(Long sessionId, List<Long> userIds);

    public void updateSessionApprovalStatus(SessionUser session);
}
