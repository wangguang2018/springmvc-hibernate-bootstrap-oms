package com.wangguang.model.enums;

public enum PrizeTypeEnum {
    MONEY(1, "钻石"), POINTS(2, "积分"),FRAGMENT(3, "碎片"), NONE(4,"未中奖"),WUFU(5,"五福碎片");
    public int value;
    public String label;

    PrizeTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static PrizeTypeEnum getByType(Integer type){
        for(PrizeTypeEnum prizeTypeEnum : PrizeTypeEnum.values()){
            if(prizeTypeEnum.value == type)
                return prizeTypeEnum;
        }
        return null;
    }
}
