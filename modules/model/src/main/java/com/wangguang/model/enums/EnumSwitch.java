package com.wangguang.model.enums;

/**
 * Enum - 开关
 *
 * @author Demon
 * @since 24/01/2018
 */
public enum EnumSwitch {
    OFF(0, "关闭"), ON(1, "开启")
    ;
    public Integer value;
    public String name;

    EnumSwitch(Integer value, String name) {
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
