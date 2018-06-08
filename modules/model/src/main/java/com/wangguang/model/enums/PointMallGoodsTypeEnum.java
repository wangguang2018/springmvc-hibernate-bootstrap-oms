package com.wangguang.model.enums;

public enum PointMallGoodsTypeEnum {
    DOLL(1, "娃娃"),DIAMOND(2, "钻石");

    public int value;
    public String label;

    PointMallGoodsTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }
}
