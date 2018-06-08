package com.wangguang.model.enums;

/**
 * Enum - 任务状态
 *
 * @author xingkong1221
 * @since 2016-09-06
 */
public enum EnumChargeMark {

    putong( 1, ""), rexiao(2, "热销"),tuijian(3, "推荐"),
    xiangou(4,"限购"),
    xianshi(5,"限时"),
    shouchong(6,"首充"),
    daily_first_recharge(7, "每日首充");

    public int value;
    public String label;

    EnumChargeMark(int value, String label) {
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
        for (EnumChargeMark mark : EnumChargeMark.values()) {
            if (mark.getValue() == value) {
                return mark.getLabel();
            }
        }
        return "未知";
    }
}
