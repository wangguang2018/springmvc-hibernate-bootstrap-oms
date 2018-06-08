package com.wangguang.model.enums;

/**
 * Enum - 领取状态
 *
 * @author Demon
 * @since 18/01/2018
 */
public enum EnumReceived {
    YES(1, "已领取"), NO(0, "未领取")
    ;
    public Integer value;
    public String name;

    EnumReceived(Integer value, String name) {
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
