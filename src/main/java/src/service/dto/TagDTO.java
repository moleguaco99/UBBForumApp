package src.service.dto;
import java.io.Serializable;

public class TagDTO implements Serializable {
    public String tagName;

    public TagDTO(String tagName) {
        this.tagName = tagName;
    }
}
