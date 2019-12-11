package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.Answer;
import src.repository.AnswerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAnswersForQuestion(long questionId){
        return answerRepository.findAll().stream().filter(a -> a.getIdQuestionOrAnswer()==questionId).collect(Collectors.toList());
    }
}
