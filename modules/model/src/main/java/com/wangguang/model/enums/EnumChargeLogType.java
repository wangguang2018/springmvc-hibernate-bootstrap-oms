package com.wangguang.model.enums;

/**
 * Enum - 充值类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumChargeLogType {
    alipay( 1, "支付宝"),
    wechat(2, "微信"),
    wanbaAndroid(3, "玩吧安卓"),
    wanbaIos(4, "玩吧IOS"),
    jiusanqiqi(5, "9377");

    public int value;
    public String label;

    EnumChargeLogType(int value, String label) {
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
        for (EnumChargeLogType mark : EnumChargeLogType.values()) {
            if (mark.getValue() == value) {
                return mark.getLabel();
            }
        }
        return null;
    }
}
