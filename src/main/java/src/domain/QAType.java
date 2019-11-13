package src.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum QAType {
    Q("question"), A("answer");

    private String type;

    QAType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
