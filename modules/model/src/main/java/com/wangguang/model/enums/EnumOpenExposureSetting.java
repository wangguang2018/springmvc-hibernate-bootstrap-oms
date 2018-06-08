package com.wangguang.model.enums;

/**
 * Enum - 曝光值设置
 *
 * @author wangguang
 * @since 01/27/2018
 */
public enum EnumOpenExposureSetting {
    OFF(0, "关闭"), ON(1, "开启")
    ;
    public Integer value;
    public String name;

    EnumOpenExposureSetting(Integer value, String name) {
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
