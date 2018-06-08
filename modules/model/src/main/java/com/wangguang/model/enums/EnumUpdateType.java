package com.wangguang.model.enums;

/**
 * Enum - 代理商下的机器主板更新类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumUpdateType {
    All(1, "全部更新"), Part(2, "部分更新");

    public int value;
    public String label;

    EnumUpdateType(int value, String label) {
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
