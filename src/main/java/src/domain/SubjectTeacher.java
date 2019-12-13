package src.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subject_teacher")
public class SubjectTeacher {
    @Id
    @Column(name = "subject_teacher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSubjectTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher")
    Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject")
    Subject subject;

    public SubjectTeacher(Teacher teacher, Subject subject) {
        this.teacher = teacher;
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubjectTeacher)) return false;
        SubjectTeacher that = (SubjectTeacher) o;
        return idSubjectTeacher.equals(that.idSubjectTeacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSubjectTeacher);
    }

    public Long getIdSubjectTeacher() {
        return idSubjectTeacher;
    }

    public void setIdSubjectTeacher(Long idSubjectTeacher) {
        this.idSubjectTeacher = idSubjectTeacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
