package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}