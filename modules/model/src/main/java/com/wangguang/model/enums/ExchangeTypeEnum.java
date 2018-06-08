package com.wangguang.model.enums;

public enum ExchangeTypeEnum {
    POINT(1, "积分"),FRAGMENT(2, "碎片"),DOLL(3, "娃娃");

    public int value;
    public String label;

    ExchangeTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }
}
