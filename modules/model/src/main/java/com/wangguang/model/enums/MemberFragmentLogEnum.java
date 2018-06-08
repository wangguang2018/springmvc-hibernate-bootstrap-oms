package com.wangguang.model.enums;

public enum  MemberFragmentLogEnum {
    INCOME((byte)1, "获得"), EXPENSE((byte)2, "消费")
    ;
    public byte value;
    public String label;

    MemberFragmentLogEnum(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
