package com.wangguang.model.enums;

/**
 * Enum - 分辨率
 *
 * @author douya
 * @since 2018-01-16
 */
public enum EnumEncodeLevel {

    A(0, "240 x 320"), B(1, "360 x 480"),C(2, "480 x 640"),
    D(3, "720 x 960"), E(4, "720 x 1280"),F(5, "1280 x 1920"),userDefined(6,"自定义");

    public int value;
    public String label;

    EnumEncodeLevel(int value, String label) {
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
        for (EnumEncodeLevel mark : EnumEncodeLevel.values()) {
            if (mark.getValue() == value) {
                return mark.getLabel();
            }
        }
        return null;
    }
}
