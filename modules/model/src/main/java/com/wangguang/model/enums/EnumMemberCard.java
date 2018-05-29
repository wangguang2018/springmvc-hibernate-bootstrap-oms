package com.wangguang.model.enums;

/**
 * Enum - 会员卡
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumMemberCard {
    WEEK( 1, "周卡"), MOUTH(2, "月卡");

    public int value;
    public String label;

    EnumMemberCard(int value, String label) {
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
