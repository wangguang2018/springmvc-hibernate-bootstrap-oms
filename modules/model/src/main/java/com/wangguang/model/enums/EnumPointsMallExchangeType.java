package com.wangguang.model.enums;

/**
 * 娃娃记录来源
 *
 * @author Demon
 * @since 17/01/2018
 */
public enum EnumPointsMallExchangeType {

    POINTS(1, "积分"), FRAGMENT(2, "碎片"),WAWA(3,"娃娃")

    ;
    public Integer value;
    public String name;

    EnumPointsMallExchangeType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
