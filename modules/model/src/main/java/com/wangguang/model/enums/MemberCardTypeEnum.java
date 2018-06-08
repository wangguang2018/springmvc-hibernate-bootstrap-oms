package com.wangguang.model.enums;

/**
 * Enum - 会员卡类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum MemberCardTypeEnum {

    WeekCard((byte) 1, "周卡"),
    MonthCard((byte)2, "月卡");

    public byte value;
    public String label;

    MemberCardTypeEnum(byte value, String label) {
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
