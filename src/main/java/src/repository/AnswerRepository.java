package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Answer;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByIdQuestionOrAnswer();
}
