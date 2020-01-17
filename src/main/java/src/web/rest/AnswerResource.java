package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.domain.*;
import src.repository.UserRepository;
import src.repository.VoteAnswerRepository;
import src.service.AnswerService;
import src.service.MailService;
import src.service.QuestionService;
import src.service.VoteAnswerService;
import src.service.dto.AnswerDTO;

import java.time.Instant;
import java.util.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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

    @Autowired
    private MailService mailService;

    @Autowired
    private QuestionService questionService;

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

    @GetMapping("/mentionedusers")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll().subList(1, userRepository.findAll().size()), HttpStatus.OK);
    }

    @PostMapping("/tagusers")
    @Transactional
    public ResponseEntity tagUsers(@RequestBody Map<String, Object> users) {
        List<String> taggedUsers = (List<String>) users.get("users");
        for (String taggedUser :
            taggedUsers) {
            String email = userRepository.findAll().stream().filter(user -> (user.getFirstName() + " " + user.getLastName()).equals(taggedUser)).map(User::getEmail).findFirst().get();
            mailService.sendEmail(email, "You have been mentioned", "Hello " + taggedUser + "! You have been mentioned in a comment. Check it out!", false, false);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/replies/{id}")
    public ResponseEntity<List<Answer>> getReplies(@PathVariable long id) {
        return new ResponseEntity<>(answerService.getRepliesForAnswer(id), HttpStatus.OK);
    }

    @PostMapping("/create-some-answers")
    @Transactional
    public ResponseEntity<?> createSomeAnswers() {
        List<Answer> answerList = new ArrayList<>();

        Optional<Question> optionalQuestion1 = questionService.findByText("Este o idee buna sa cream corutine in Kotlin folosind GlobalScope?");
        Optional<User> optionalUser1 = userRepository.findOneByLogin("fred");
        Answer answer1 = new Answer();
        answer1.setIdQuestionOrAnswer(optionalQuestion1.get().getIdQuestion());
        answer1.setUserP(optionalUser1.get());
        answer1.setTimestamp(Instant.now());
        answer1.setText("Eu iti recomand sa folosesti viewModelScope deoarece" +
            " pot sa apara probleme in cazul in care metodele care sunt apelate arunca exceptii.");
        answer1.setType(QAType.Q);
        answer1.setRating(0);

        Optional<Question> optionalQuestion2 = questionService.findByText("De ce ar trebui sa folosesc smart pointers in C++?");
        Optional<User> optionalUser2 = userRepository.findOneByLogin("janette");
        Answer answer2 = new Answer();
        answer2.setIdQuestionOrAnswer(optionalQuestion2.get().getIdQuestion());
        answer2.setUserP(optionalUser2.get());
        answer2.setTimestamp(Instant.now());
        answer2.setText("Sunt foarte buni deoarece se dealoca automat" +
            " memoria referita de acel pointer in cazul unei exceptii sau in cazul unui return si astfel se evita memory leak-ul.");
        answer2.setType(QAType.Q);
        answer2.setRating(0);

        Optional<Question> optionalQuestion3 = questionService.findByText("Cunoasteti niste resurse si tutoriale pentru proiectele de la materia Programare pentru dispozitive mobile?");
        Optional<User> optionalUser3 = userRepository.findOneByLogin("claudiu");
        Answer answer3 = new Answer();
        answer3.setIdQuestionOrAnswer(optionalQuestion3.get().getIdQuestion());
        answer3.setUserP(optionalUser3.get());
        answer3.setTimestamp(Instant.now());
        answer3.setText("Pentru android iti recomand sa citesti " +
            "documentatia oficiala si posibil sa te ajute sectiunea de Training care apartine tot de ei.");
        answer3.setType(QAType.Q);
        answer3.setRating(0);

        Optional<User> optionalUser4 = userRepository.findOneByLogin("julie");
        Answer answer4 = new Answer();
        answer4.setIdQuestionOrAnswer(optionalQuestion3.get().getIdQuestion());
        answer4.setUserP(optionalUser4.get());
        answer4.setTimestamp(Instant.now());
        answer4.setText("Pentru React Native exemplele de cod de pe site-ul " +
            "celor de la facebook sunt foarte utile. Poti incerca si React Native - The Practical Guide.");
        answer4.setType(QAType.Q);
        answer4.setRating(0);

        Answer answerReturned1 = answerService.saveAndReturn(answer1);
        Answer answerReturned2 = answerService.saveAndReturn(answer2);
        Answer answerReturned3 = answerService.saveAndReturn(answer3);
        Answer answerReturned4 = answerService.saveAndReturn(answer4);

        answerList.add(answerReturned1);
        answerList.add(answerReturned2);
        answerList.add(answerReturned3);
        answerList.add(answerReturned4);

        return new ResponseEntity<>(answerList, HttpStatus.CREATED);
    }
}
