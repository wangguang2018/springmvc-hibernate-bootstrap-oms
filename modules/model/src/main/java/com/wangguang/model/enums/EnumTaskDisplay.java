package com.wangguang.model.enums;

/**
 * Enum - 任务系统的显示状态
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumTaskDisplay {

    HIDE(0, "隐藏"),SHOW(1, "显示");

    private int value;
    private String label;

    EnumTaskDisplay(int value, String label) {
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
        for (EnumTaskDisplay agent : EnumTaskDisplay.values()) {
            if (agent.getValue() == value) {
                return agent.getLabel();
            }
        }
        return null;
    }
}
