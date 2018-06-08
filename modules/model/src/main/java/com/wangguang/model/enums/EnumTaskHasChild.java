package com.wangguang.model.enums;

/**
 * Enum - 任务系统的是否有子任务
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumTaskHasChild {

    NO(0, "没有"),YES(1, "有");

    private int value;
    private String label;

    EnumTaskHasChild(int value, String label) {
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
        for (EnumTaskHasChild agent : EnumTaskHasChild.values()) {
            if (agent.getValue() == value) {
                return agent.getLabel();
            }
        }
        return null;
    }
}
