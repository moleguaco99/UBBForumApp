package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Question;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByText(String text);
}
