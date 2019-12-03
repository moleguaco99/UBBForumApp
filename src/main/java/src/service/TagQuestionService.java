package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.TagQuestion;
import src.repository.TagQuestionRepository;

@Service
@Transactional
public class TagQuestionService {

    @Autowired
    private TagQuestionRepository repository;

    public TagQuestion save(TagQuestion tagQuestion) {
        return repository.saveAndFlush(tagQuestion);
    }
}
