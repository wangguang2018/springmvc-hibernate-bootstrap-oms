package com.wangguang.model.enums;

/**
 * Enum - 会员卡
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumDollLogExchangeStatus {

    NO( (byte)0, "未兑换"), YES((byte)1, "已经兑换");

    public byte value;
    public String label;

    EnumDollLogExchangeStatus(byte value, String label) {
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
