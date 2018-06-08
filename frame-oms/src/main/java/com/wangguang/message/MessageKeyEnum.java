package com.wangguang.message;

/**
 * 消息发布和订阅的KEY
 *
 * @Desc com.dm.web.message
 * @Author peakren
 * @Date 09/02/2018 6:19 PM
 */
public enum MessageKeyEnum {

    PUB_NETTY_DOLL("netty-doll-machine", "发布到游戏端");

    public String key;
    public String name;

    MessageKeyEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

}
