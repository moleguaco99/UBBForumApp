package src.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import src.domain.Question;
import src.domain.Tag;
import src.domain.TagQuestion;
import src.service.TagService;
import src.service.dto.QuestionDTO;
import src.service.dto.TagDTO;
import src.service.mapper.QuestionMapper;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/ourApi")
public class TagResource {

    @Autowired
    private TagService tagService;

    @Autowired
    private QuestionMapper questionMapper;

    @PostMapping("/tag")
    @Transactional
    public ResponseEntity<Tag> save(@RequestBody TagDTO tagDTO) {
        try {
            Tag tag = new Tag();
            tag.setTagName(tagDTO.tagName);
            Tag tagReturned = tagService.save(tag);
            return new ResponseEntity<>(tagReturned, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create-some-tags")
    @Transactional
    public ResponseEntity<List<Tag>> createSomeTags() {
        try {
            Tag tag1 = new Tag();
            tag1.setTagName("c++");
            tag1.setTagQuestions(new HashSet<>());
            Tag tag2 = new Tag();
            tag2.setTagName("assembly");
            tag2.setTagQuestions(new HashSet<>());
            Tag tag3 = new Tag();
            tag3.setTagName("observer");
            tag3.setTagQuestions(new HashSet<>());
            Tag tag4 = new Tag();
            tag4.setTagName("kotlin");
            tag4.setTagQuestions(new HashSet<>());
            Tag tag5 = new Tag();
            tag5.setTagName("react_native");
            tag5.setTagQuestions(new HashSet<>());
            Tag tag6 = new Tag();
            tag6.setTagName("python");
            tag6.setTagQuestions(new HashSet<>());

            List<Tag> tagList = new ArrayList<>();
            Optional<Tag> tag1Returned = tagService.saveIfNotExistWithTagName(tag1);
            tag1Returned.ifPresent(tagList::add);
            Optional<Tag> tag2Returned = tagService.saveIfNotExistWithTagName(tag2);
            tag2Returned.ifPresent(tagList::add);
            Optional<Tag> tag3Returned = tagService.saveIfNotExistWithTagName(tag3);
            tag3Returned.ifPresent(tagList::add);
            Optional<Tag> tag4Returned = tagService.saveIfNotExistWithTagName(tag4);
            tag4Returned.ifPresent(tagList::add);
            Optional<Tag> tag5Returned = tagService.saveIfNotExistWithTagName(tag5);
            tag5Returned.ifPresent(tagList::add);
            Optional<Tag> tag6Returned = tagService.saveIfNotExistWithTagName(tag6);
            tag6Returned.ifPresent(tagList::add);
            return new ResponseEntity<>(tagList, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tags")
    public ResponseEntity<List<TagDTO>> findAll() {
        List<Tag> tagList = tagService.findAll();
        List<TagDTO> tagDTOList = tagList
            .stream()
            .map(tag -> new TagDTO(tag.getTagName()))
            .collect(Collectors.toList());
        return new ResponseEntity<>(tagDTOList, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagID}/questions")
    public ResponseEntity<List<QuestionDTO>> findAllQuestionsForOneTag(@PathVariable("tagID") Long tagID) {
        try {
            List<Question> questionList = tagService.findAllQuestionsForOneTag(tagID);
            questionList.forEach(question -> question.setTagQuestionSet(new HashSet<>()));
            List<QuestionDTO> questionDTOList = questionMapper.questionListToQuestionDTOList(questionList);
            return new ResponseEntity<>(questionDTOList, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
