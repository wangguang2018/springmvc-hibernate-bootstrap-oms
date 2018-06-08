package com.wangguang.model.enums;

/**
 * 积分商城商品类型
 *
 * @author Demon
 * @since 17/01/2018
 */
public enum EnumPMGoodsType {

    PRODUCT(1, "娃娃"), DIAMOND(2, "钻石")

    ;
    public Integer value;
    public String name;

    EnumPMGoodsType(Integer value, String name) {
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
