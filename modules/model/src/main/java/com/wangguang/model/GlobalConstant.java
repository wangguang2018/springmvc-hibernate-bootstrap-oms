package com.wangguang.model;

/**
 * 全局常量
 *
 * @author xingkong1221
 * @date 2015-11-16
 */
public class GlobalConstant {

    public static final String IOS_VERSION = "1.5.1";//IOS版本号
    public static final String ANDROID_VERSION = "5";//安卓版本号

    public static final String EXPRESS_MONEY = "express_money";//快递费用
    public static final String INVITE_POINTS = "invite_points";//邀请送多少钻石
    public static final String INVITE_COUNTS = "invite_counts";//邀请最大次数
    public static final String CONSUME_POINTS = "consume_points";//消费送多少倍积分
    public static final String NEW_USER_MONEY = "new_user_money";//新用户送钻石
    public static final String WX_CERT_PATH = "wx_cert_path";//微信证书存放路径
    public static final String FREE_EXPRESS_NUMBER = "free_express_number";//兑换娃娃包邮的数量

    //上海玩吧对接相关参数
    public static final Integer SHARE_COUNTS = 10;//每日分享次数
    public static final Integer SHARE_POINTS = 10;//每日分享积分
    public static final Integer COLLECTION_MONEY = 10;//收藏送钻石

    // 9377游戏支付需要用到的几个参数
    public static final String GAME_KEY_9377 = "3866508893f5a843ab38431b9aa653be";//9377回调约定的key
    public static final String game_prefix = "com.ydd.amount";//支付拼接前缀
    public static final String game_Suffix = ".option";//支付拼接后缀

    //默认图片
    public static final String DEFALUT_HEAD_URL = "https://yingdd.oss-cn-hangzhou.aliyuncs.com/b46e76cf03a731487f9f19b7127e95fc.png";

    //机构账号
    public static final String AGORA_APP_ID = "dad6aa43944a44e3aaa8ab8cdddccc06";
    public static final String AGORA_APP_CERTIFICATE = "bf16c7f2152745e38fa856a3f47d8496";


    public static final String JPUSH_KEY = "505677f4c8b0fb90172ec6b3";
    public static final String JPUSH_SECRET = "31687251e79539dae082fb56";


    /**
     * 代理商分享信息
     */
    public static final String AGENT_SHARE_INFORMATION_KEY = "AGENT_SHARE_INFO";







    /**
     * 状态常量定义 1:正常 0:禁用
     *
     * @author ranfi
     */
    public enum Status {

        Enable(1, "可用"), Disable(0, "禁用"), Deleted(-1, "删除");

        public int value;
        public String name;

        Status(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getStatusName(int status) {
            return status == Status.Enable.value ? Status.Enable.name : Status.Disable.name;
        }
    }

    /**
     * 删除状态
     * <p>
     * 1: 已删除   0: 未删除
     */
    public enum DeleteStatus {
        Normal(0, "未删除"), Deleted(1, "已删除");
        private Integer value;
        private String name;

        DeleteStatus(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 性别
     */
    public enum Gender {
        MALE(1, "男"), FEMALE(2, "女"), UNKNOWN(0, "未知");

        public int value;
        public String name;

        Gender(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getGenderName(int value) {
            for (Gender gender : Gender.values()) {
                if (gender.value == value) {
                    return gender.name;
                }
            }
            return "未知";
        }
    }

    /**
     * 平台类型
     */
    public enum PlatType {

        WEIXIN(1, "微信"), IOS(2, "苹果"), ANDROID(3, "安卓"), OTHERS(4, "其他");

        public int type;
        public String name;

        PlatType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getValue() {
            return type;
        }

        public void setValue(int value) {
            this.type = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static String getPlatType(int type) {
            for (PlatType platType : PlatType.values()) {
                if (platType.type == type) {
                    return platType.name;
                }
            }
            return "其他";
        }

        public static PlatType getPlatType(String name) {
            for (PlatType platType : PlatType.values()) {
                if (platType.name().equalsIgnoreCase(name)) {
                    return platType;
                }
            }
            return PlatType.OTHERS;
        }
    }
}
