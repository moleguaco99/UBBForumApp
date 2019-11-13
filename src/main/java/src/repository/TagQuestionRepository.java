package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.TagQuestion;

public interface TagQuestionRepository extends JpaRepository<TagQuestion, Long> {
}
