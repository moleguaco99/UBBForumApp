package src.domain;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "id_teacher")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTeacher;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "rank")
    private String rank;

    @Column(name = "web_page")
    private String webPage;

    @Column(name = "photo_path")
    private String photoPath;

    @OneToMany(mappedBy = "teacher")
    private Set<SubjectTeacher> subjectTeachers = new HashSet<>();

    public Teacher() {

    }
    public Teacher(String firstName, String lastName, String email, String webPage, String photoPath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.webPage = webPage;
        this.photoPath = photoPath;
        this.rank = "default";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return idTeacher.equals(teacher.idTeacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTeacher);
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }
}
