package com.wangguang.model.enums;

/**
 * Enum - 短信类型
 *
 * @author xingkong1221
 * @since 2017-04-07
 */
public enum SMSTypeEnum {
    VERIFICATION(1, "验证类短信"), NOTIFICATION(2, "通知类短信")
    ;
    public Integer value;
    public String name;

    SMSTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
