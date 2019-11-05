package src.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vote_answer")
public class VoteAnswer {
    @Id
    @Column(name = "voteAID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_v")
    User userV;

    @ManyToOne
    @JoinColumn(name = "answer")
    Answer answer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteAnswer)) return false;
        VoteAnswer that = (VoteAnswer) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
