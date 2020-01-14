package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Question;
import src.domain.Tag;
import src.domain.TagQuestion;
import src.repository.TagQuestionRepository;
import src.repository.TagRepository;
import src.service.dto.TagDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagRepository repository;

    @Autowired
    private TagQuestionRepository tagQuestionRepository;

    public List<Tag> findAllIdsForTags(Set<TagDTO> tagDTOS) {
        List<Tag> tags = new ArrayList<>();
        for (TagDTO tagDTO : tagDTOS) {
            Tag tag = repository.findTagByTagName(tagDTO.tagName);
            if (Objects.isNull(tag)) {
                throw new RuntimeException("null tag");
            } else {
                tags.add(tag);
            }
        }
        return tags;
    }

    public Tag save(Tag tag) {
        return repository.saveAndFlush(tag);
    }

    public Optional<Tag> saveIfNotExistWithTagName(Tag tag) {
        Tag tagSearched = repository.findTagByTagName(tag.getTagName());
        if (Objects.isNull(tagSearched)) {
            Tag tagReturned = repository.saveAndFlush(tag);
            return Optional.of(tagReturned);
        }
        return Optional.empty();
    }

    public List<Question> findAllQuestionsForOneTag(Long tagID) {
        Optional<Tag> optionalTagSearched = repository.findById(tagID);
        if (optionalTagSearched.isPresent()) {
            List<TagQuestion> tagQuestionList = tagQuestionRepository.findAllByTag(optionalTagSearched.get());
            return tagQuestionList.stream().map(TagQuestion::getQuestion).collect(Collectors.toList());
        } else {
            throw new RuntimeException("There is no tag with tagID: ".concat(tagID.toString()));
        }
    }

    public List<Question> findAllQuestionsForOneTag(String tagName) {
        Tag tagSearched = repository.findTagByTagName(tagName);
        if (Objects.nonNull(tagSearched)) {
            List<TagQuestion> tagQuestionList = tagQuestionRepository.findAllByTag(tagSearched);
            return tagQuestionList.stream().map(TagQuestion::getQuestion).collect(Collectors.toList());
        } else {
            throw new RuntimeException("There is no tag with tagName: ".concat(tagName));
        }
    }

    public Optional<Tag> findOne(String tagName) {
        return Optional.ofNullable(repository.findTagByTagName(tagName));
    }

    public List<Tag> findAll() {
        return repository.findAll();
    }
}
