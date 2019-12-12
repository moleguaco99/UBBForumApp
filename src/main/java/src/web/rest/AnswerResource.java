package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Answer;
import src.repository.UserRepository;
import src.service.AnswerService;
import src.service.dto.AnswerDTO;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import src.domain.User;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class AnswerResource {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    public AnswerResource(){
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<List<Answer>> getAnswersToQuestion(@PathVariable long id){
        return new ResponseEntity<>(answerService.getAnswersForQuestion(id), HttpStatus.OK);
    }

    @PostMapping("/answer")
    @Transactional
    public ResponseEntity<Long> add(@RequestBody AnswerDTO answerDTO){
        Answer answer = new Answer();
        answer.setText(answerDTO.text);
        answer.setTimestamp(Instant.now());
        answer.setIdQuestionOrAnswer(answerDTO.idQA);
        answer.setRating(answerDTO.rating);
        answer.setType(answerDTO.type);

        Optional<User> user = userRepository.findById(answerDTO.idUser);
        if(user.isPresent()){
            answer.setUserP(user.get());
            answerService.save(answer);
        }
        return new ResponseEntity<>(answer.getIdAnswer(), HttpStatus.OK);
    }
}
