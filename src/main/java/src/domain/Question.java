package src.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Question implements Serializable {

    @Id
    @Column(name = "id_question")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestion;

    @Column(name="timestamp")
    Instant timestamp;

    @Column(name="text")
    String text;

    @ManyToOne
    @JoinColumn(name = "user_p")
    private User userP;

    @OneToMany(mappedBy = "question")
    private Set<TagQuestion> tagQuestionSet = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return idQuestion.equals(question.idQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuestion);
    }

    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUserP() {
        return userP;
    }

    public void setUserP(User userP) {
        this.userP = userP;
    }

    public Set<TagQuestion> getTagQuestionSet() {
        return tagQuestionSet;
    }

    public void setTagQuestionSet(Set<TagQuestion> tagQuestionSet) {
        this.tagQuestionSet = tagQuestionSet;
    }
}
