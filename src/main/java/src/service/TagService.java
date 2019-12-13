package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.domain.Tag;
import src.repository.TagRepository;
import src.service.dto.TagDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> findAllIdsForTags(Set<TagDTO> tagDTOS) {
        List<Tag> tags = new ArrayList<>();
        for (TagDTO tagDTO : tagDTOS) {
            Tag tag = repository.findTagByTagName(tagDTO.tagName);
            if(Objects.isNull(tag)) {
                throw new RuntimeException("null tag");
            }
            else {
                tags.add(tag);
            }
        }
        return tags;
    }
}
