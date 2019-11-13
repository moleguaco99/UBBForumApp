package src.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @Column(name = "id_tag")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTag;

    @Column(name = "tag_name")
    private String tagName;

    @OneToMany(mappedBy = "tag")
    private Set<TagQuestion> tagQuestions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return idTag.equals(tag.idTag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTag);
    }

    public Long getIdTag() {
        return idTag;
    }

    public void setIdTag(Long idTag) {
        this.idTag = idTag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<TagQuestion> getTagQuestions() {
        return tagQuestions;
    }

    public void setTagQuestions(Set<TagQuestion> tagQuestions) {
        this.tagQuestions = tagQuestions;
    }
}
