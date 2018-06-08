package com.wangguang.model.enums;

/**
 * Enum - 房间通知状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum RoomNotificationStatusEnum {

    shutdown((byte) 0, "停用"),

    startup((byte)1, "启动");

    public byte value;
    public String label;

    RoomNotificationStatusEnum(byte value, String label) {
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
