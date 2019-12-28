package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.Answer;
import src.domain.VoteAnswer;
import src.repository.UserRepository;
import src.repository.VoteAnswerRepository;
import src.service.AnswerService;
import src.service.VoteAnswerService;
import src.service.dto.AnswerDTO;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import src.domain.User;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class AnswerResource {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteAnswerService voteAnswerService;

    public AnswerResource() {
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<List<Answer>> getAnswersToQuestion(@PathVariable long id) {
        return new ResponseEntity<>(answerService.getAnswersForQuestion(id), HttpStatus.OK);
    }

    @PostMapping("/answer")
    @Transactional
    public ResponseEntity<Long> add(@RequestBody AnswerDTO answerDTO) {
        Answer answer = new Answer();
        answer.setText(answerDTO.text);
        answer.setTimestamp(Instant.now());
        answer.setIdQuestionOrAnswer(answerDTO.idQA);
        answer.setRating(answerDTO.rating);
        answer.setType(answerDTO.type);

        Optional<User> user = userRepository.findById(answerDTO.idUser);
        if (user.isPresent()) {
            answer.setUserP(user.get());
            answerService.save(answer);
        }
        return new ResponseEntity<>(answer.getIdAnswer(), HttpStatus.OK);
    }

    @PostMapping("/vote")
    @Transactional
    public ResponseEntity update(@RequestBody Map<String, Object> answerRatingMap) {
        Map<String, Object> map = (Map<String, Object>) answerRatingMap.get("answerRatingMap");

        Integer id = (Integer) map.get("idAnswer");
        Integer idUser = (Integer) map.get("idUser");
        Integer vote = (Integer) map.get("vote");

        VoteAnswer voteAnswer = new VoteAnswer();
        User user = userRepository.getOne((long) idUser);
        Answer answer = answerService.getAnswer((long) id);

        voteAnswer.setUserV(user);
        voteAnswer.setAnswer(answer);

        try {
            if (vote == -1) {
                voteAnswerService.delete(voteAnswer);
            } else {
                voteAnswerService.save(voteAnswer);
            }
            answerService.update((long) id, vote);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/mentionusers")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll().subList(1, userRepository.findAll().size()), HttpStatus.OK);
    }

    @GetMapping("/replies/{id}")
    public ResponseEntity<List<Answer>> getReplies(@PathVariable long id){
        return new ResponseEntity<>(answerService.getRepliesForAnswer(id), HttpStatus.OK);
    }
}
