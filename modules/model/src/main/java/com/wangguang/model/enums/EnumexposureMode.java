package com.wangguang.model.enums;

/**
 * Enum - 曝光模式
 *
 * @author wangguang
 * @since 2018-01-27
 */
public enum EnumexposureMode {

    userDefined(1, "自定义曝光"), auto(3, "自动曝光");

    private int value;
    private String label;

    EnumexposureMode(int value, String label) {
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
        for (EnumexposureMode mode : EnumexposureMode.values()) {
            if (mode.getValue() == value) {
                return mode.getLabel();
            }
        }
        return null;
    }
}
