package com.wangguang.model.enums;

public enum PrizeOptionStatusEnum {
    ON((byte) 1, "开启"), OFF((byte)2, "关闭");
    public byte value;
    public String label;

    PrizeOptionStatusEnum(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public static PrizeOptionStatusEnum getByType(Integer type){
        for(PrizeOptionStatusEnum statusEnum : PrizeOptionStatusEnum.values()){
            if(statusEnum.value == type)
                return statusEnum;
        }
        return null;
    }
}
