package com.wangguang.model.enums;

/**
 * Enum - 会员卡类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum MemberCardMarkEnum {

    Nomal((byte) 1, "普通"),

    Hot((byte)2, "热销"),

    Recommend((byte)3, "推荐");

    public byte value;
    public String label;

    MemberCardMarkEnum(byte value, String label) {
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
