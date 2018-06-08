package com.wangguang.model.enums;

/**
 * Enum - 会员积分记录类型
 *
 * @author xingkong1221
 * @since 2016-09-01
 */
public enum EnumMoneyLogType {

    Income(1, "游戏币获取"), Expense(2, "游戏币消费");

    public byte value;
    public String name;

    EnumMoneyLogType(Integer value, String name) {
        this.value = value.byteValue();
        this.name = name;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
