package com.wangguang.model.enums;

/**
 * Enum - 任务状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumBannerType {

    machine( 1, "机器"), charge(2, "充值"),url(3, "url"),image(4,"不跳转");

    public int value;
    public String label;

    EnumBannerType(int value, String label) {
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
        for (EnumBannerType type : EnumBannerType.values()) {
            if (type.getValue() == value) {
                return type.getLabel();
            }
        }
        return null;
    }
}
