package src.service.mapper;

import org.springframework.stereotype.Service;
import src.domain.Question;
import src.domain.Tag;
import src.domain.TagQuestion;
import src.service.dto.QuestionDTO;
import src.service.dto.QuestionDTOMoreInfo;
import src.service.dto.TagDTO;
import src.service.dto.TagDTOMoreInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionMapper {
    public QuestionDTOMoreInfo questionToQuestionDTOMoreInfo(Question question) {
        QuestionDTOMoreInfo questionDTOMoreInfo = new QuestionDTOMoreInfo();
        questionDTOMoreInfo.idQuestion = question.getIdQuestion();
        questionDTOMoreInfo.timestamp = question.getTimestamp();
        questionDTOMoreInfo.text = question.getText();
        questionDTOMoreInfo.idUser = question.getUserP().getId();
        questionDTOMoreInfo.tagSet = question
            .getTagQuestionSet()
            .stream()
            .map(tagQuestion -> tagToTagDTOMoreInfo(tagQuestion.getTag()))
            .collect(Collectors.toSet());
        return questionDTOMoreInfo;
    }

    public TagDTOMoreInfo tagToTagDTOMoreInfo(Tag tag) {
        TagDTOMoreInfo tagDTOMoreInfo = new TagDTOMoreInfo();
        tagDTOMoreInfo.idTag = tag.getIdTag();
        tagDTOMoreInfo.tagName = tag.getTagName();
        return tagDTOMoreInfo;
    }

    public QuestionDTO questionToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.text = question.getText();
        questionDTO.idUser = question.getUserP().getId();
        questionDTO.tags = question
            .getTagQuestionSet()
            .stream()
            .map(TagQuestion::getTag)
            .map(this::tagToTagDTO)
            .collect(Collectors.toSet());
        return questionDTO;
    }

    public TagDTO tagToTagDTO(Tag tag) {
        return new TagDTO(tag.getTagName());
    }

    public List<QuestionDTO> questionListToQuestionDTOList(List<Question> questionList) {
        return questionList.stream().map(this::questionToQuestionDTO).collect(Collectors.toList());
    }
}
