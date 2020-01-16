package src.domain;

import java.io.Serializable;

public class StringWrapper implements Serializable {
    String value;

    public StringWrapper(){}

    public StringWrapper(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
