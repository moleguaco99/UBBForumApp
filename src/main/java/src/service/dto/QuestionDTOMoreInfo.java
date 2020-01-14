package src.service.dto;

import java.time.Instant;
import java.util.Set;

public class QuestionDTOMoreInfo {
    public Long idQuestion;
    public Instant timestamp;
    public String text;
    public Long idUser;
    public Set<TagDTOMoreInfo> tagSet;
}
