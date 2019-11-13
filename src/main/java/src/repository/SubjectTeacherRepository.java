package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.SubjectTeacher;

public interface SubjectTeacherRepository extends JpaRepository<SubjectTeacher, Long> {
}
