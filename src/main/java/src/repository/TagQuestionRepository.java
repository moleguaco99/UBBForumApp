package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.domain.Question;
import src.domain.Tag;
import src.domain.TagQuestion;
import java.util.List;

public interface TagQuestionRepository extends JpaRepository<TagQuestion, Long> {
    List<TagQuestion> findAllByQuestion(Question question);
    List<TagQuestion> findAllByTag(Tag tag);
}
