package com.wangguang.model.enums;


/**
 * Enum - 会员卡类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum DollLogStatusEnum {

    Success((byte) 0, "成功"),

    Fail((byte)1, "失败"),

    Gaming((byte)2, "游戏中");

    public byte value;
    public String label;

    DollLogStatusEnum(byte value, String label) {
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
