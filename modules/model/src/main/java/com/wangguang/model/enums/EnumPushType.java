package com.wangguang.model.enums;

public enum EnumPushType {

    SHENSU(1, "申诉推送"),FAHUO(2,"发货"),JIQI(3,"机器推送");

    public int value;
    public String label;

    EnumPushType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
