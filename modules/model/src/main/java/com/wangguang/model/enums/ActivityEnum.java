package com.wangguang.model.enums;

public enum ActivityEnum {

    WUFU(1, "集五福活动");

    public int value;
    public String label;

    ActivityEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static ActivityEnum getByType(Integer type){
        for(ActivityEnum activityEnum : ActivityEnum.values()){
            if(activityEnum.value == type)
                return activityEnum;
        }
        return null;
    }
}
