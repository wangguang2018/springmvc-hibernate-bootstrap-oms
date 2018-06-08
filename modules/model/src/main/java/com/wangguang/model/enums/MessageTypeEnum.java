package com.wangguang.model.enums;

public enum  MessageTypeEnum {
    SYSTEM((byte)1, "系统消息"),ORDER((byte)2, "订单消息"),EXCHANGE((byte)3, "兑换消息");

    public byte value;
    public String label;

    MessageTypeEnum(byte value, String label) {
        this.value = value;
        this.label = label;
    }
}
