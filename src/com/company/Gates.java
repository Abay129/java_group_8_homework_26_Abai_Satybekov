package com.company;

public class Gates {
    private String key;
    private String value;

    public Gates(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void updateValue(String update){
        this.value = update;
    }
}
