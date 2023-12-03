package nextstep.qna.domain.history;

import nextstep.qna.domain.ContentType;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.answer.Answer;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private Long id;
    private ContentType contentType;
    private Long contentId;
    private NsUser deletedBy;
    private LocalDateTime createdDate = LocalDateTime.now();

    public DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public DeleteHistory hasQuestion(Question question) {
        contentType = ContentType.QUESTION;
        contentId = question.getId();
        deletedBy = question.getWriter();
        return this;
    }

    public DeleteHistory hasAnswer(Answer answer) {
        contentType = ContentType.ANSWER;
        contentId = answer.getId();
        deletedBy = answer.getWriter();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedBy, that.deletedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedBy);
    }

    @Override
    public String toString() {
        return "DeleteHistory [id=" + id + ", contentType=" + contentType + ", contentId=" + contentId + ", deletedBy="
                + deletedBy + ", createdDate=" + createdDate + "]";
    }
}