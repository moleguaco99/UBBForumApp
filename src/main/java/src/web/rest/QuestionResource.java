package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import src.domain.Question;
import src.domain.Tag;
import src.domain.TagQuestion;
import src.domain.User;
import src.repository.UserRepository;
import src.service.QuestionService;
import src.service.TagQuestionService;
import src.service.TagService;
import src.service.dto.FilterDTO;
import src.service.dto.QuestionDTO;
import src.service.dto.QuestionDTOMoreInfo;
import src.service.mapper.QuestionMapper;

import java.time.Instant;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class QuestionResource {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagQuestionService tagQuestionService;

    @Autowired
    private QuestionMapper questionMapper;

    public QuestionResource() {
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> findAll() {
        List<Question> all = questionService.findAllQuestions();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/question")
    @Transactional
    public ResponseEntity<Long> add(@RequestBody QuestionDTO questionDTO) {
        Question question = new Question();
        question.setText(questionDTO.text);
        question.setTimestamp(Instant.now());

        Optional<User> user = userRepository.findById(questionDTO.idUser);
        if (user.isPresent()) {
            question.setUserP(user.get());
            questionService.save(question);
        }

        try {
            List<Tag> tags = tagService.findAllIdsForTags(questionDTO.tags);
            for (Tag tag : tags) {
                TagQuestion tagQuestion = new TagQuestion();
                tagQuestion.setQuestion(question);
                tagQuestion.setTag(tag);
                tagQuestionService.save(tagQuestion);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(question.getIdQuestion(), HttpStatus.OK);
    }

    @PostMapping("/questions/filter")
    @Transactional
    public ResponseEntity<List<Question>> filterQuestions(@RequestBody FilterDTO filterDTO) {
        List<String> filters = filterDTO.filters;
        List<Question> questions = questionService.findAllQuestions();

        List<Question> result = new ArrayList<>();

        boolean contains;
        for (Question question : questions) {
            List<String> tags = tagQuestionService.findAllTagsNameForQuestion(question);
            contains = filters.stream().allMatch(element -> tags.contains(element));

            if (contains) {
                result.add(question);
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/create-some-questions")
    @Transactional
    public ResponseEntity<List<QuestionDTOMoreInfo>> createSomeQuestions() {
        try {
            Question question1 = new Question();
            question1.setTimestamp(Instant.now());
            question1.setText("Este o idee buna sa cream corutine in Kotlin folosind GlobalScope?");
            Optional<User> optionalUser1 = userRepository.findById((long) 4);
            if (optionalUser1.isPresent()) {
                question1.setUserP(optionalUser1.get());
            } else {
                throw new RuntimeException("There is no user with id: ".concat(String.valueOf(4)));
            }
            TagQuestion tagQuestion1 = new TagQuestion();
            tagQuestion1.setQuestion(question1);
            Optional<Tag> optionalTag1 = tagService.findOne("kotlin");
            if (optionalTag1.isPresent()) {
                tagQuestion1.setTag(optionalTag1.get());
            } else {
                throw new RuntimeException("There is no tag with tagName: ".concat("kotlin"));
            }
            question1.setTagQuestionSet(new HashSet<>());

            Question question2 = new Question();
            question2.setTimestamp(Instant.now());
            question2.setText("De ce ar trebui sa folosesc smart pointers in C++?");
            question2.setUserP(optionalUser1.get());
            TagQuestion tagQuestion2 = new TagQuestion();
            tagQuestion2.setQuestion(question2);
            Optional<Tag> optionalTag2 = tagService.findOne("c++");
            if (optionalTag2.isPresent()) {
                tagQuestion2.setTag(optionalTag2.get());
            } else {
                throw new RuntimeException("There is no tag with tagName: ".concat("c++"));
            }
            question2.setTagQuestionSet(new HashSet<>());

            Question question1Returned = questionService.saveAndReturn(question1);
            Question question2Returned = questionService.saveAndReturn(question2);

            TagQuestion tagQuestion1Returned = tagQuestionService.save(tagQuestion1);
            TagQuestion tagQuestion2Returned = tagQuestionService.save(tagQuestion2);

            Set<TagQuestion> tagQuestionSet1 = new HashSet<>();
            tagQuestionSet1.add(tagQuestion1Returned);
            question1Returned.setTagQuestionSet(tagQuestionSet1);
            Set<TagQuestion> tagQuestionSet2 = new HashSet<>();
            tagQuestionSet2.add(tagQuestion2Returned);
            question2Returned.setTagQuestionSet(tagQuestionSet2);

            List<QuestionDTOMoreInfo> questionDTOMoreInfoList = new ArrayList<>();
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question1Returned));
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question2Returned));
            return new ResponseEntity<>(questionDTOMoreInfoList, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
