package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
