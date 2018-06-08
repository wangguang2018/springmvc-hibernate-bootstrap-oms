package com.wangguang.model.enums;

public enum ProductFragmentTypeEnum {
    CHANGE_PRODUCT(1, "兑换产品碎片"), OTHERS(2, "其他碎片");
    public int value;
    public String label;

    ProductFragmentTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static ProductFragmentTypeEnum getByType(Integer type){
        for(ProductFragmentTypeEnum typeEnum : ProductFragmentTypeEnum.values()){
            if(typeEnum.value == type)
                return typeEnum;
        }
        return null;
    }
}
