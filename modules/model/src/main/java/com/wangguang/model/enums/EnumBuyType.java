package com.wangguang.model.enums;

/**
 * Enum - 任务状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumBuyType {

    week( 1, "体验周卡"), mouth(2, "超值月卡"),normal(3, "钻石充值");

    public int value;
    public String label;

    EnumBuyType(int value, String label) {
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
        for (EnumBuyType mark : EnumBuyType.values()) {
            if (mark.getValue() == value) {
                return mark.getLabel();
            }
        }
        return "未知";
    }
}
