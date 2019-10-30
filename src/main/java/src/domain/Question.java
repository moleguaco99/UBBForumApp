package src.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "questions")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Question implements Serializable {

    @Id
    @Column(name = "questionID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="timestamp")
    Timestamp timestamp;

    @Column(name="text")
    String text;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
