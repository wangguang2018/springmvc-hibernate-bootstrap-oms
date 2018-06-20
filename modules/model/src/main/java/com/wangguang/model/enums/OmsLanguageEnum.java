package com.wangguang.model.enums;

public enum OmsLanguageEnum {


    en(1, "en"), zh(2, "zh")
    ;
    public Integer value;
    public String name;

    OmsLanguageEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
