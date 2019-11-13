package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import src.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
