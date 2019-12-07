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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
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
        if(user.isPresent()) {
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

            if(contains) {
                result.add(question);
            }

        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
