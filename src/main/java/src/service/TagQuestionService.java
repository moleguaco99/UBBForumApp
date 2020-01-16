package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Question;
import src.domain.TagQuestion;
import src.repository.TagQuestionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagQuestionService {

    @Autowired
    private TagQuestionRepository repository;

    public TagQuestion save(TagQuestion tagQuestion) {
        return repository.saveAndFlush(tagQuestion);
    }

    public List<TagQuestion> saveAll(List<TagQuestion> tagQuestionListReceived) {
        List<TagQuestion> tagQuestionList = new ArrayList<>();
        tagQuestionListReceived.forEach(tagQuestionFromListReceived -> {
            TagQuestion tagQuestionSaved = this.repository.saveAndFlush(tagQuestionFromListReceived);
            tagQuestionList.add(tagQuestionSaved);
        });
        return tagQuestionList;
    }

    public List<String> findAllTagsNameForQuestion(Question question) {
        List<TagQuestion> tagQuestions = repository.findAllByQuestion(question);
        return tagQuestions.stream()
            .map(tagQuestion -> tagQuestion.getTag().getTagName())
            .collect(Collectors.toList());
    }
}
