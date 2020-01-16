package src.web.rest;

import org.checkerframework.checker.nullness.Opt;
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

    private List<Optional<User>> optionalUserList;
    private List<Optional<Tag>> optionalTagList;
    private List<TagQuestion> tagQuestionList;

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
    public ResponseEntity<?> createSomeQuestions() {
        try {
            this.optionalUserList = new ArrayList<>();
            this.optionalTagList = new ArrayList<>();
            this.tagQuestionList = new ArrayList<>();

            Question question1 = create1();
            Question question2 = create2();
            Question question3 = create3();
            Question question4 = create4();
            Question question5 = create5();

            Question question1Returned = questionService.saveAndReturn(question1);
            Question question2Returned = questionService.saveAndReturn(question2);
            Question question3Returned = questionService.saveAndReturn(question3);
            Question question4Returned = questionService.saveAndReturn(question4);
            Question question5Returned = questionService.saveAndReturn(question5);

            List<TagQuestion> tagQuestionListReturned = tagQuestionService.saveAll(this.tagQuestionList);

            Set<TagQuestion> tagQuestionSet1 = new HashSet<>();
            tagQuestionSet1.add(tagQuestionListReturned.get(0));
            question1Returned.setTagQuestionSet(tagQuestionSet1);
            Set<TagQuestion> tagQuestionSet2 = new HashSet<>();
            tagQuestionSet2.add(tagQuestionListReturned.get(1));
            question2Returned.setTagQuestionSet(tagQuestionSet2);
            Set<TagQuestion> tagQuestionSet3 = new HashSet<>();
            tagQuestionSet3.add(tagQuestionListReturned.get(2));
            tagQuestionSet3.add(tagQuestionListReturned.get(3));
            question3Returned.setTagQuestionSet(tagQuestionSet3);
            Set<TagQuestion> tagQuestionSet4 = new HashSet<>();
            tagQuestionSet4.add(tagQuestionListReturned.get(4));
            question4Returned.setTagQuestionSet(tagQuestionSet4);
            Set<TagQuestion> tagQuestionSet5 = new HashSet<>();
            tagQuestionSet5.add(tagQuestionListReturned.get(5));
            question5Returned.setTagQuestionSet(tagQuestionSet5);

            List<QuestionDTOMoreInfo> questionDTOMoreInfoList = new ArrayList<>();
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question1Returned));
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question2Returned));
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question3Returned));
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question4Returned));
            questionDTOMoreInfoList.add(questionMapper.questionToQuestionDTOMoreInfo(question5Returned));
            return new ResponseEntity<>(questionDTOMoreInfoList, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private Question create1() {
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

        optionalUserList.add(optionalUser1);
        optionalTagList.add(optionalTag1);
        tagQuestionList.add(tagQuestion1);

        return question1;
    }

    private Question create2() {
        Question question2 = new Question();
        question2.setTimestamp(Instant.now());
        question2.setText("De ce ar trebui sa folosesc smart pointers in C++?");
        if (optionalUserList.get(0).isPresent()) {
            question2.setUserP(optionalUserList.get(0).get());
        }
        TagQuestion tagQuestion2 = new TagQuestion();
        tagQuestion2.setQuestion(question2);
        Optional<Tag> optionalTag2 = tagService.findOne("c++");
        if (optionalTag2.isPresent()) {
            tagQuestion2.setTag(optionalTag2.get());
        } else {
            throw new RuntimeException("There is no tag with tagName: ".concat("c++"));
        }
        question2.setTagQuestionSet(new HashSet<>());

        optionalTagList.add(optionalTag2);
        tagQuestionList.add(tagQuestion2);

        return question2;
    }

    private Question create3() {
        Question question3 = new Question();
        question3.setTimestamp(Instant.now());
        question3.setText("Cunoasteti niste resurse si tutoriale pentru proiectele de la materia Programare pentru dispozitive mobile?");
        Optional<User> optionalUser2 = userRepository.findById((long) 1601);
        if (optionalUser2.isPresent()) {
            question3.setUserP(optionalUser2.get());
        } else {
            throw new RuntimeException("There is no user with id: ".concat(String.valueOf(1601)));
        }
        TagQuestion tagQuestion3s1 = new TagQuestion();
        tagQuestion3s1.setQuestion(question3);
        Optional<Tag> optionalTag3 = tagService.findOne("react_native");
        if (optionalTag3.isPresent()) {
            tagQuestion3s1.setTag(optionalTag3.get());
        } else {
            throw new RuntimeException("There is no tag with tagName: ".concat("c++"));
        }
        TagQuestion tagQuestion3s2 = new TagQuestion();
        tagQuestion3s2.setQuestion(question3);
        if (optionalTagList.get(0).isPresent()) {
            tagQuestion3s2.setTag(optionalTagList.get(0).get());
            question3.setTagQuestionSet(new HashSet<>());
        }

        optionalUserList.add(optionalUser2);
        optionalTagList.add(optionalTag3);
        tagQuestionList.add(tagQuestion3s1);
        tagQuestionList.add(tagQuestion3s2);

        return question3;
    }

    private Question create4() {
        Question question4 = new Question();
        question4.setTimestamp(Instant.now());
        question4.setText("Imi poate explica cineva cum folosim gramatici atributate in fisierele Bison?");
        if (optionalUserList.get(1).isPresent()) {
            question4.setUserP(optionalUserList.get(1).get());
        }
        TagQuestion tagQuestion4 = new TagQuestion();
        tagQuestion4.setQuestion(question4);
        if (optionalTagList.get(1).isPresent()) {
            tagQuestion4.setTag(optionalTagList.get(1).get());
        }
        question4.setTagQuestionSet(new HashSet<>());

        tagQuestionList.add(tagQuestion4);

        return question4;
    }

    private Question create5() {
        Question question5 = new Question();
        question5.setTimestamp(Instant.now());
        question5.setText("Exista in React Native vreun mecanism in asa fel incat sa stochez informatii" +
            " despre starea aplicatiei si la schimbarea acestora sa se reactualizeze componentele grafice?");
        Optional<User> optionalUser3 = userRepository.findById((long) 1701);
        if (optionalUser3.isPresent()) {
            question5.setUserP(optionalUser3.get());
        } else {
            throw new RuntimeException("There is no user with id: ".concat(String.valueOf(1701)));
        }
        question5.setUserP(optionalUser3.get());
        TagQuestion tagQuestion5 = new TagQuestion();
        tagQuestion5.setQuestion(question5);
        if (optionalTagList.get(2).isPresent()) {
            tagQuestion5.setTag(optionalTagList.get(2).get());
        }
        question5.setTagQuestionSet(new HashSet<>());

        optionalUserList.add(optionalUser3);
        tagQuestionList.add(tagQuestion5);

        return question5;
    }
}
