package com.wangguang.model.enums;

/**
 * Enum - 流的旋转角度
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumStreamAngleOfRotationType {

    zero(0, "0"),
    ninety(1, "90"),
    oneHundredAndEighty(2, "180"),
    twoHundredAndSeventy(3, "270"),
    ;

    private int value;
    private String label;

    EnumStreamAngleOfRotationType(int value, String label) {
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
        for (EnumStreamAngleOfRotationType rotationType : EnumStreamAngleOfRotationType.values()) {
            if (rotationType.getValue() == value) {
                return rotationType.getLabel();
            }
        }
        return null;
    }
}
