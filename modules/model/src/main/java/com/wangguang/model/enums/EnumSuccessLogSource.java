package com.wangguang.model.enums;

/**
 * 娃娃记录来源
 *
 * @author Demon
 * @since 17/01/2018
 */
public enum EnumSuccessLogSource {

    GRAB(1, "抓取"), EXCHANGE(2, "兑换")

    ;
    public Integer value;
    public String name;

    EnumSuccessLogSource(Integer value, String name) {
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
