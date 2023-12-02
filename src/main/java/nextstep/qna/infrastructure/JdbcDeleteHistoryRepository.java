package nextstep.qna.infrastructure;

import nextstep.qna.domain.DeletedHistory;
import nextstep.qna.domain.DeleteHistoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("deleteHistoryRepository")
public class JdbcDeleteHistoryRepository implements DeleteHistoryRepository {
    @Override
    public void saveAll(List<DeletedHistory> deleteHistories) {}
}
