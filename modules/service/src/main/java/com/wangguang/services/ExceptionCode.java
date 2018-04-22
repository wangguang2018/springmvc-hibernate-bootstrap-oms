package com.wangguang.services;

/**
 * 异常码
 */
public enum ExceptionCode {

    NORMAL(0, "OK"),
    SAVE_SUCCESS(0, "保存成功"),

    // 系统级
    DEFAULT_ERROR_MSG(10000, "服务器被外星人劫走了，请稍后再试!"),
    SERVEREXCEPTION(10001, "服务器忙!"), // 实则服务器异常
    METHODDOESNOTEXIST(10002, "Method does not exist!"), // 接口不存在
    CLIENTAPIERROR(10003, "client api error!"), // 接口不存在
    CLIENT_EXCEPTION(10004, "客户端异常"),
    PARAM_EXCEPTION(10005, "参数异常"),
    API_EXCEPTION(10006, "api exception"),
    DATA_ERROR(10007, "数据错误"),

    MISSING_ACCESS_TOKEN(20001, "请先登录！"), // 缺少access_token
    INVALID_ACCESS_TOKEN(20002, "登录失效，请重新登录！"), // 无效的accessToken
    INVALID_REFRESH_TOKEN(20003, "登录失效，请重新登录！"), // 无效的refresh_token
    PARAMEXCEPTION(20101, "Param to bean exception!"), // Param参数转换异常
    USERNOTEMPTY(20102, "User cannot be empty!"),
    ONEPARAMISEMPTY(20103, "One param is empty!"),
    MISSINGPARAMETER(20104, "Missing parameter!"),
    UPLOADFILEEXCEPTION(20105, "upload file exception!"),
    MACHINE_INVALID(20106, "机器状态不是空闲!"),
    AGENT_KEY_INVALID(20107, "agent key is invalid!"),
    WX_CODE_INVALID(20108, "code is invalid!"),

    pinglv(41007, "你的访问频率过高，请稍后再试！"),

    // 会员接口异常 40****
    DISABLED_MEMBER(40001, "账号被禁用"),
    NONE_MEMBER_INFO(40002, "帐号信息不存在"),
    ACCOUNT_EXISTED(40003, "帐号已经存在"),
    EMAIL_EXISTED(40004, "邮箱已经存在"),
    MEMBER_UN_ACTIVATE(40007, "帐号未激活"),
    INVALID_VERIFYCATION_CODE(40003, "无效的验证码"),
    EXISTS_MEMBER_ACCOUNT(40004, "手机号码已注册"),
    PASSWORD_NOT_EQUAL(40005, "前后两次密码不一致"),
    INVALID_MEMBER_PASSWORD(40006, "密码错误"),
    SEND_BIND_SMS_FAILURE(40008, "短信验证码发送失败"),
    INVALID_REGISTER_TOKEN(40009, "无效的注册token"),
    INVALID_PASSWORD_LENGTH(40010, "密码长度需6～15位"),
    INVALID_GENDER_VALUE(40011, "无效的性别参数"),
    NOT_BIND_MOBILE_MEMBER(40012, "非绑定手机用户"),
    NOT_ENOUGH_BALANCE(40013, "游戏币不足"),
    NOT_STOCK(40111, "商品库存不足"),
    NOT_ENOUGH_POINTS(40014, "积分不足"),
    INVITATION_EXISTS(40015, "老朋友，您的手机号已领取了"),
    CREATE_MEMBER_ERROR(400016, "创建用户失败，请稍候再试"),
    INVALID_INVITE_CODE(400017, "无效的邀请码"),
    UESD_INVITE_CODE(400018, "已邀请过"),
    INVALID_MACHINE(400019, "机器正在维护中"),
    START_GAME_ERROR(40029, "开始游戏失败"),
    UESD_INVITE_CODE_(400020, "不能互相邀请"),
    INVITE_COUNTS_LIMIT(400021, "邀请次数超过限制"),
    EXCEED_COUNTS(400023, "今日分享次数已用完"),
    HAS_COLLECTION(400022, "你已经收藏过了"),

    NO_GIFT(400033, "无法领取礼物"),
    RECEIVE_ONLY_ONE(400034, "你已经领取过礼包，明天请记得再来领取喔~"),
    RECEIVE_SUCCESS(400035, "领取成功"),

    VIP_dislpay(400044, "很遗憾，你的VIP特权已失效，点亮特权后重新领取！"),

    VIP_NO(400047, "很遗憾，你非VIP用户，不能领取！"),
    VIP_LEVEL_NO(400045, "VIP等级不匹配，无法领取！"),
    VIP_ERROR(400046, "系统繁忙，请稍后重试或联系客服进行处理！"),
    // 地址接口异常 40*1**
    NONE_MEMBER_ADDRESS(40101, "地址信息不存在"),
    ILLEGAL_ADDRESS_UPDATE(40102, "非法修改其人地址"),
    NONE_AREA_INFO(40103, "地区信息不存在"),

    member_MONEY_NOT_BUY(44444, "账号余额不足，请充值！"),

    XIANGOU_MEMBER(40013, "每个用户只能购买一次限购产品！"),

    NOT_FIRST_BUY(40014, "你不是首次充值用户！"),

    BUY_ERROR(44445, "购买道具失败！"),

    NO_OPTION(44446, "没有此充值选项！"),

    STORE_ERROR(40023, "线下支付异常！"),
    //领取任务奖励
    Task_IS_RECEIVE(48888, "奖励已经被领取过了！"),
    Task_IS_NO_COMPLETE(48889, "未达到领取条件！"),
    CANNOT_RECEIVE(41023, "你不能领取奖励！"), //
    TODAY_RECEIVE_COMPLETET(41022, "今日已领取过！"), //
    DATA_ERROR_RESET(41023,"数据错误，请重新设置"),

    // 签到 41***
    HAS_SIGNED(41001, "今日已签到"),

    // 推广记录异常 42***
    NONE_MEMBER_MARKET_RECORD_INFO(42001, "推广记录信息不存在"),
    DUPLICATED_FIRST_ORDER(42002, "重复绑定首次下单"),

    // 晒单异常 43***
    NONE_SHARE_INFO(43001, "晒单信息不存在"),
    SHARE_IS_CHECKING(43002, "晒单信息审核中"),

    // 购物车异常 44***
    INVALID_CART_QUANTITY(44001, "清单数量必须大于0"),
    INVALID_AUCTION_STATUS(44002, "夺宝已经结束，请等待新一期的夺宝活动"),


    // 收藏接口异常 45***
    INVALID_FAVORITE_TYPE(45001, "无效的收藏类型"),

    // 优惠券异常 46***
    NONE_COUPON_INFO(46001, "优惠券信息不存在"),
    TIME_NOT_START(46002,"优惠券未到开始使用时间"),


    NONE_GOODS_INFO(50001, "商品信息不存在"),
    DISABLED_GOODS(50002, "商品已下架"),
    NONE_GOODS_CATEGORY_INFO(50003, "商品分类信息不存在"),
    NONE_ENOUGH_GOODS(50004, "产品库存不足"),

    // 竞拍品异常
    NONE_AUCTION_INFO(51001, "夺宝商品信息不存在"),
    NONE_AUCTION_DATE_INFO(51002, "本期夺宝活动已经结束"),


    // 秒杀 52***
    NONE_FLASH_GOODS_INFO(52001, "秒杀商品不存在"),
    INVALID_FLASH_GOODS_SECTION(52002, "无效的时间段"),
    HAS_RESERVATION(52003, "您已获取了该秒杀商品的抢购权"),
    FLASH_SALE_HAS_OVER(52004, "秒杀已经结束了"),
    FLASH_SALE_IS_PROCESSING(52005, "秒杀已经开始了"),
    FLASH_GOODS_IS_OFF_SALE(52006, "秒杀商品已经下架"),

    // 专卖 53****
    NONE_RESALE_INFO(53001, "转卖信息不存在"),




    // 积分商城 54***
    NONE_POINTS_MALL_GOODS_INFO(54001, "积分商品不存在"),

    //任务 55*******
    NONE_TASK(55001,"没有此任务"),
    NOT_PERMISSION_ALLOW(55002, "只有代理商有添加和修改权限"),
    SAME_TASK_EXIST(55003,"相同的任务已经存在"),

    //奖品设置 56******
    YET_SETTING(56001,"没有配置，请前去配置"),
    MONEY_NO_DATA(56002,"请先去设置钻石才可修改权重"),
    POINTS_NO_DATA(56003,"请先去设置积分才可修改权重"),
    FRAGENT_NO_DATA(56004,"请先去设置碎片才可修改权重"),
    WUFU_FRAGENT_NO_DATA(56005,"请先去设置五福碎片才可修改权重"),
    WEIGHT_SET_ERROR(56006,"权重不可都为0"),

    //商家站点 57******
    NO_PERMISSION_ALLOW(57001,"只有代理商有添加和修改的权限"),
    EMPTY_MACHINE_INFO(57002,"机器不能为空"),
    MACHINE_BINDED(57003,"机器已经被绑定"),



    // 二维码 6****
    NONE_QRCODE(60001, "二维码信息不存在"),
    EXPIRED_QRCODE(60002, "二维码已经失效"),
    MISMATCH_CITY_ID(60003, "二维码无法在本地区使用"),
    NONE_CITY_BIND(60004, "请先填写地区信息"),
//    WORKER_ID_EMPTY(60001, "工人ID为空"),
//    WORKER_ORDER_ILLEGAL_UPDATE(60002, "工人订单非法更新"),
//    DELIVERY_STATUS_EXCEPTION(600003, "派送状态异常"),
//    NOT_FOUND_DELIVERYED_WORKER(60003, "未发现可派送的工人"),
//    NO_PERMISSION_DELIVERED(60004, "此工单不属于你配送"),


    // 订单相关的异常 7*****
    BARCODE_CANNOT_BE_EMPTY(700001, "条形码不能为空"),
    BARCODE_NOT_EXISTS(700002, "条形码不存在"),
    ORDER_NOT_EXISTS(700003, "订单不存在"),
    NONE_ORDER_SN_EXISTS(70004, "订单序列号不存在"),

    // 充值异常
    RECHARGE_MONEY_ERROR(74001, "充值金额必须大于0"),
    NONE_RECHARGE_LOG(74002, "充值记录不存在"),

    //机器 75*******
    MACHINE_MARK_EXSIT(75001,"该机器编号已经存在"),

    //渠道商 753********
    NO_CHANNEL(75301,"该代理商没有渠道商"),



    //上传excel
    ORDER_SN_NOT_STRING(76001,"订单号数据类型错误"),
    EXCEL_CELL_DATA_TYPE_ERROR(76002,"EXCEL表数据类型错误"),
    EXCEL_FILE_STREAM_ERROR(76003,"EXCEL表上传流错误"),
    EXPRESS_COMPANY_ERROR(76004,"请选择正确的快递公司"),
    EXCEL_CELL_ORDER_SN_ERROR(76005,"EXCEL表中订单号格式不对"),
    EXCEL_CELL_EXPRESS_TYPE_FORMAT_ERROR(76006,"EXCEL表中物流公司数据格式不对"),
    EXCEL_CELL_EXPRESS_TYPE_DATA_ERROR(76007,"EXCEL表中物流公司数据不对"),
    EXCEL_CELL_EXPRESS_NO_ERROR(76008,"EXCEL表中运单号格式不对"),


    // 游戏 8*****
    NONE_PRIZE_CHIP(81001, "碎片信息不存在"),
    EXCHANGE_FAILURE(81002, "奖品兑换失败"),
    RECEIPT_FAILURE(81003, "收货失败"),
    NOT_ENOUGH_DOLL_NUM(81005, "娃娃数量不足"),
    NOT_ENOUGH_DOLL_(81006, "兑换的娃娃数量不匹配"),

    NONE_WHEEL_CHIP(82001, "奖品信息不存在"),
    ERROR_WHEEL_PRIZE(82002, "奖品信息配置错误"),
    MOT_WIN(82003, "很遗憾，您没有抽中任何奖品，再接再励哦！"),
    NOT_ENOUGH_FRAGMENT(82004, "所需碎片数量不足，无法兑换"),
    ACTIVITY_IS_END(82005, "活动已经结束"),

    //会员卡
    DATA_OUT_NUMBER(83001, "同一类型的会员卡只允许一张"),
    MEMBER_CARD_TYPE_CHANGE_LIMIT(83002, "会员卡的类型不能修改"),
    PERMISSION_NOT_ALLOW(83003, "只有一级代理商有添加和修改权限"),


    // 全局异常 9****
    INVALID_MOBILE(90001, "手机号码格式不正确"),
    MISSING_PARAMETER(90006, "缺少必要参数"),
    INVALID_PARAMETER(90007, "参数错误"),
    CODE_INCORRECT(90011, "验证码错误"),
    INVALID_LATITUDE(90016, "纬度格式不正确"),
    INVALID_LONGITUDE(90017, "经度格式不正确"),

    ALREADY_JOIN_ACT(100001, "已经报名该活动"),
    ACTIVITY_STARTED(100002,"活动以开始"),
    ACTIVITY_END(100003,"活动以结束");
    public final Integer errorCode;
    public final String errorMsg;

    ExceptionCode(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }



    public static ExceptionCode getExceptionCode(final String exceptionCode) {
        try {
            return ExceptionCode.valueOf(exceptionCode);
        } catch (Exception e) {
        }

        return SERVEREXCEPTION;
    }


}
