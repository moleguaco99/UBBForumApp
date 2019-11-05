package src.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column(name = "id_subject")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSubject;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "semester")
    private Integer semester;

    @Column(name = "section")
    private String section;

    @Column(name = "language")
    private String language;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "subject")
    private Set<SubjectTeacher> subjectTeachers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;
        Subject subject = (Subject) o;
        return idSubject.equals(subject.idSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubject);
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }
}

