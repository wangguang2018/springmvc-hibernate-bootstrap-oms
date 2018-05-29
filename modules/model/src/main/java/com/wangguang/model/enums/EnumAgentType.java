package com.wangguang.model.enums;

/**
 * Enum - 代理商类型
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumAgentType {

    daili(1, "代理商"), qudao(2, "渠道商");

    private int value;
    private String label;

    EnumAgentType(int value, String label) {
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
        for (EnumAgentType agent : EnumAgentType.values()) {
            if (agent.getValue() == value) {
                return agent.getLabel();
            }
        }
        return "未知";
    }
}
