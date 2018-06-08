package com.wangguang.model.enums;

/**
 * Enum - 产品类型
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumProductType {

    doll( (byte)1, "娃娃"), exchangeProduct((byte)2, "兑换商品"),exchangeBigDoll((byte)3, "兑换大娃娃");

    public byte value;
    public String label;

    EnumProductType(byte value, String label) {
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
        for (EnumProductType type : EnumProductType.values()) {
            if (type.getValue() == value) {
                return type.getLabel();
            }
        }
        return null;
    }
}
