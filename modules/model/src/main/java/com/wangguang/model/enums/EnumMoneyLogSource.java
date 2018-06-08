package com.wangguang.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum - 积分记录
 *
 * @author xingkong1221
 * @since 2016-09-01
 */
public enum EnumMoneyLogSource {

    EXPRESS(1, "快递费"), RECHARGE(2,"充值"),GAME(3,"游戏"),REFUND(4,"退款"),INVITE(5,"邀请送钻石"),SIGN(6,"签到送钻石"),WEEK(38,"周卡"),MOUTH(39,"月卡"),
    COLLECTION(10,"收藏"),EXCHANGE(11,"娃娃兑换"), POINTS_EXCHANGE(12, "积分兑换"),
    DAY_TASK(20,"每日任务"),GRAB_REWARD(21,"抓取奖励"),GRAB_SUCCESS_REWARD(22,"抓取奖励"),
    CHARGE_REWARD(23,"充值奖励"),PLATFORM_GIFT(24,"平台赠送"),
    ExclusiveGift(42,"QQ专属礼包"),noviceGift(41,"QQ新手礼包"),dayGift(43,"QQ每日礼包"),vipGift(44,"QQvip礼包"),
    privilegeGift(45,"QQ特权礼包"),FRAGMENT_EXCHANGE(48,"碎片兑换"),NO_CATCH_SEND(50,"未抓中赠送")
    ;

    public byte value;
    public String name;

    EnumMoneyLogSource(Integer value, String name) {
        this.value = value.byteValue();
        this.name = name;
    }

    public static Map<Integer,String> getMap(){
        Map<Integer,String> map = new HashMap<>();
        for(EnumMoneyLogSource e : EnumMoneyLogSource.values()){
            map.put((int)e.value,e.name);
        }
        return map;
    }

    public byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
