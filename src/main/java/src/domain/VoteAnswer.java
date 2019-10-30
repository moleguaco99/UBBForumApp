package src.domain;

import javax.persistence.*;

public class VoteAnswer {
    @Id
    @Column(name = "voteAID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    User user;

    Answer answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
