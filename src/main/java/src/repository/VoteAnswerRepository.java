package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.VoteAnswer;

public interface VoteAnswerRepository extends JpaRepository<VoteAnswer, Long> {
}
