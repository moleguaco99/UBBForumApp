package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Subject;
import src.domain.SubjectTeacher;
import src.domain.Teacher;

import java.util.List;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public Optional<Subject> findById(Long id);
}
