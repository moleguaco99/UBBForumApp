package src.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_question")
public class TagQuestion {
    @Id
    @Column(name = "id_tag_question")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTagQuestion;

    @ManyToOne
    @JoinColumn(name = "tag")
    Tag tag;

    @ManyToOne
    @JoinColumn(name = "question")
    Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagQuestion)) return false;
        TagQuestion that = (TagQuestion) o;
        return idTagQuestion.equals(that.idTagQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTagQuestion);
    }

    public Long getIdTagQuestion() {
        return idTagQuestion;
    }

    public void setIdTagQuestion(Long idTagQuestion) {
        this.idTagQuestion = idTagQuestion;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
