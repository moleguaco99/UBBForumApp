package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
