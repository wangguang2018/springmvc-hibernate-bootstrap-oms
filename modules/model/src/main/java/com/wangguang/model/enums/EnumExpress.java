package com.wangguang.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumExpress {


    BSHT((byte)9,"百世汇通"),SF((byte) 1, "顺丰快递"), ST((byte)2, "申通快递"),YD((byte)3, "韵达快运"),ZT((byte)4, "中通速递"),TT((byte)5, "天天快递"),EMS((byte)6, "ems快递"),
    YT((byte) 7, "圆通速递"),DBWL((byte)8, "德邦物流"),YZPY((byte) 10, "邮政平邮"),OTHERS((byte)100,"其他");

    public byte value;
    public String label;

    EnumExpress(byte value, String label) {
        this.value = value;
        this.label = label;
    }

    public static Map<Integer,String> getMap(){
        Map<Integer,String> map = new HashMap<>();
        for(EnumExpress e : EnumExpress.values()){
            map.put((int)e.value,e.label);
        }
        return map;
    }

    public byte getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static String getLabel(byte value) {
        for (EnumExpress express : EnumExpress.values()) {
            if (express.getValue() == value) {
                return express.getLabel();
            }
        }
        return null;
    }
}
