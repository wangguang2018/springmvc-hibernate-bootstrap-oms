package com.wangguang.model.enums;


/**
 * Enum - 申诉处理类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum AppealHandleTypeEnum {

    Reject((byte)1, "驳回"),

    Pass((byte)2, "通过"),

    Refund((byte) 3, "退款");

    public byte value;
    public String label;

    AppealHandleTypeEnum(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
