package com.wangguang.model.enums;

/**
 * Enum - 短信平台
 *
 * @author xingkong1221
 * @since 2017-04-07
 */
public enum SMSPlatformEnum {
    UNKNOWN(0, "未知"), ALIDAYU(1, "阿里大于"), CCP(2, "容联云通讯"), YUNPIAN(3, "云片"), ALIYUN(4, "阿里云"),
    CHUANG_LAN(5, "创蓝253"), MONT_NETS(6, "梦网")
    ;
    public byte value;
    public String name;

    SMSPlatformEnum(Integer value, String name) {
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
