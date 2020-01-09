package src.service.dto;

import java.io.Serializable;
import java.util.List;

public class FilterDTO implements Serializable {
    public List<String> filters;

    public FilterDTO(List<String> filters) {
        this.filters = filters;
    }

    public FilterDTO() {
    }
}
