package com.wangguang.model.enums;

/**
 * Enum - 任务关键字
 *
 * @author douya
 * @since 2018-01-16
 */
public enum EnumTaskKey {

    sign("sign", "签到"), charge_card("charge_card", "充值卡"),day_task("day_task", "每日任务"),
    share( "share", "分享app"), invite("invite", "邀请"),draw("draw", "抽奖"),
    grab("grab","抓取商品"),grab_reward("grab_reward", "抓取奖励"),grab_success_reward("grab_success_reward", "抓取成功奖励"),
    charge_reward("charge_reward", "钻石充值"),

    daily_share_rewards("daily_share_rewards", "每日分享礼包"), daily_share_friends("daily_share_friends", "分享微信好友"),
    daily_share_moments("daily_share_moments", "分享朋友圈")
    ;

    public String value;
    public String label;

    EnumTaskKey(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }


    public static String getLabel(String value) {
        for (EnumTaskKey mark : EnumTaskKey.values()) {
            if (mark.getValue().equals(value)) {
                return mark.getLabel();
            }
        }
        return null;
    }
}
