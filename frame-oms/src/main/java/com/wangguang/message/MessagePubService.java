package com.wangguang.message;

/**
 * 消息发布接口
 *
 * @Desc com.dm.web
 * @Author peakren
 * @Date 09/02/2018 6:11 PM
 */
public interface MessagePubService {


    void pub(String key, Object data);
}
