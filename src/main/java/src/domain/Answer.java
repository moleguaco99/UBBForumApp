package src.domain;


import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "answer")
public class Answer {

    @OneToMany(mappedBy = "answer")
    private Set<VoteAnswer> voteAnswers = new HashSet<>();

    @Id
    @Column(name = "id_answer")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAnswer;

    @Column(name = "id_question_or_answer")
    private Long idQuestionOrAnswer;

    @ManyToOne
    @JoinColumn(name = "user_p")
    private User userP;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private QAType type;

    @Column(name = "rating")
    private Integer rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return idAnswer.equals(answer.idAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnswer);
    }

    public Set<VoteAnswer> getVoteAnswers() {
        return voteAnswers;
    }

    public void setVoteAnswers(Set<VoteAnswer> voteAnswers) {
        this.voteAnswers = voteAnswers;
    }

    public Long getIdAnswer() {
        return idAnswer;
    }

    public void setIdAnswer(Long idAnswer) {
        this.idAnswer = idAnswer;
    }

    public Long getIdQuestionOrAnswer() {
        return idQuestionOrAnswer;
    }

    public void setIdQuestionOrAnswer(Long idQuestionOrAnswer) {
        this.idQuestionOrAnswer = idQuestionOrAnswer;
    }

    public User getUserP() {
        return userP;
    }

    public void setUserP(User userP) {
        this.userP = userP;
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

    public QAType getType() {
        return type;
    }

    public void setType(QAType type) {
        this.type = type;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
