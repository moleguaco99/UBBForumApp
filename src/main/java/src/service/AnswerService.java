package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.domain.Answer;
import src.domain.QAType;
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
        return answerRepository.findAll().stream().filter(a -> a.getType().equals(QAType.Q) && a.getIdQuestionOrAnswer()==questionId).collect(Collectors.toList());
    }

    public List<Answer> getRepliesForAnswer(long answerId){
        return answerRepository.findAll().stream().filter(a -> a.getType().equals(QAType.A) && a.getIdQuestionOrAnswer()==answerId).collect(Collectors.toList());
    }

    public void save(Answer a){
        answerRepository.save(a);
        answerRepository.flush();
    }

    public void update(Long answerId, Integer vote){
        Answer answer = answerRepository.getOne(answerId);
        answer.setRating(answer.getRating()+vote);
        answerRepository.saveAndFlush(answer);
    }

    public Answer getAnswer(Long id) {
        return answerRepository.findById(id).get();
    }
}
