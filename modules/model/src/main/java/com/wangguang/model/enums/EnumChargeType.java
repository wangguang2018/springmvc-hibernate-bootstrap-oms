package com.wangguang.model.enums;

/**
 * Enum - 平台
 *
 * @author xingkong1221
 * @since 2016-08-31
 */
public enum EnumChargeType {
// 1支付宝  2微信  3:玩吧安卓 4：玩吧IOS 5:9377
    alipay((byte)1, "支付宝"), wechat((byte)2, "微信"), wanba((byte)3, "玩吧"), game9377((byte)5, "9377"),
   ;

    public byte value;
    public String name;

    EnumChargeType(byte value, String name) {
        this.value = value;
        this.name = name;
    }

    public static EnumChargeType getType(byte value) {
        for (EnumChargeType map : EnumChargeType.values()) {
            if (map.value == value) {
                return map;
            }
        }
        return null;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


}
