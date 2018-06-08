package com.wangguang.model.enums;

/**
 * Enum - 短信状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumSmsStatus {
    Failure((byte)-1, "发送失败"), PendingSend((byte)0, "待发送"), Success((byte)1, "发送成功")
    ;
    public byte value;
    public String label;

    EnumSmsStatus(byte value, String label) {
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
