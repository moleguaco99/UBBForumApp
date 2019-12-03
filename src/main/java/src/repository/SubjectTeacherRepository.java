package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;

import java.util.List;

public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
    List<SubjectTeacher> findAllByTeacher(Teacher teacher);
    List<SubjectTeacher> findAllBySubject(Subject subject);
}
