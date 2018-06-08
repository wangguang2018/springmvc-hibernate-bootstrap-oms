package com.wangguang.model.enums;

/**
 * Enum - 充值状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumChargeLogStatus {

    yet( 0, "未支付"), payed(1, "已支付");

    public int value;
    public String label;

    EnumChargeLogStatus(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }


    public static String getLabel(int value) {
        for (EnumChargeLogStatus mark : EnumChargeLogStatus.values()) {
            if (mark.getValue() == value) {
                return mark.getLabel();
            }
        }
        return null;
    }
}
