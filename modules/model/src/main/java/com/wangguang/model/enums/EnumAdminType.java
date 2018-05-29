package com.wangguang.model.enums;

public enum EnumAdminType {
    ADMIN(1, "管理员"), AGENT(2, "代理商"),Qudao(3, "渠道商");
    public int value;
    public String label;

    EnumAdminType(int value, String label) {
        this.value = value;
        this.label = label;
    }

}
