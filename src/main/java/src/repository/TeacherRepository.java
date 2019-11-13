package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
