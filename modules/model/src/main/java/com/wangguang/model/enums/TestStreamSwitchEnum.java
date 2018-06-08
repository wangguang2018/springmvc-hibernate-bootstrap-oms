package com.wangguang.model.enums;

/**
 * Enum - 机器设置：是否使用测试流开关
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum TestStreamSwitchEnum {

    OFF(0, "否"),

    ON(1, "是");

    public int value;
    public String label;

    TestStreamSwitchEnum(int value, String label) {
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
