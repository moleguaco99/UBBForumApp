package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Question;
import src.repository.QuestionRepository;

import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }

    public void save(Question question) {
        questionRepository.save(question);
        questionRepository.flush();
    }

    public Question saveAndReturn(Question question) {
        return questionRepository.saveAndFlush(question);
    }
}
