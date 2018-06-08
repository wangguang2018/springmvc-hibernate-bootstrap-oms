package com.wangguang.model.enums;

/**
 * Enum - oauth认证
 *
 * @author xingkong1221
 * @since 2016-10-12
 */
public enum EnumOAuthType {
    QQ(1, "QQ"), MicroMessage(2, "微信"), Weibo(3, "微博")
    ;
    public byte value;
    public String label;

    EnumOAuthType(Integer value, String label) {
        this.value = value.byteValue();
        this.label = label;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 判断是否包含此认证
     *
     * @param type 类型
     * @return 是否包含
     */
    public static boolean contains(byte type) {
        for(EnumOAuthType oauthType : EnumOAuthType.values()) {
            if (oauthType.getValue() == type) {
                return true;
            }
        }
        return false;
    }
}
