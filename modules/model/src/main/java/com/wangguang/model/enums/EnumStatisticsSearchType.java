package com.wangguang.model.enums;

public enum EnumStatisticsSearchType {
    Diamond(1, "钻石数"), Catch(2, "抓取数");
    public int value;
    public String label;

    EnumStatisticsSearchType(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
