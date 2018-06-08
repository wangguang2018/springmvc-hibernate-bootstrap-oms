package com.wangguang.model.enums;

/**
 * Enum - 积分记录类型
 *
 * @author Demon
 * @since 17/01/2018
 */
public enum EnumPointRecordsType {
    INCREASE(1, "增加"), DECREASE(2, "减少")
    ;
    public Integer value;
    public String name;

    EnumPointRecordsType(Integer value, String name) {
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
