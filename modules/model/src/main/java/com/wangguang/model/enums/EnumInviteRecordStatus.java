package com.wangguang.model.enums;

/**
 * 邀请记录状态
 *
 * @author Demon
 * @since 23/03/2018
 */
public enum EnumInviteRecordStatus {
    REGISTERED(1, "已注册"), REWARDED(2, "已奖励")
    ;
    public Integer value;
    public String name;

    EnumInviteRecordStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
