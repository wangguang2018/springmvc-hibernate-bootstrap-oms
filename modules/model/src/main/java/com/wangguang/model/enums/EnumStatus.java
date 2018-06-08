package com.wangguang.model.enums;

/**
 * 状态
 *
 * @author Demon
 * @since 17/01/2018
 */
public enum EnumStatus {

    ENABLE(1, "启用"), DISABLE(0, "禁用")

    ;
    public Integer value;
    public String name;

    EnumStatus(Integer value, String name) {
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
