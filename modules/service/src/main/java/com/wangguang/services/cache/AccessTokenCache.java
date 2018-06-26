package com.wangguang.services.cache;

import com.wangguang.model.RedisKeys;
import org.springframework.stereotype.Service;

/**
 * Cache - access_token
 *
 * @author xingkong1221
 * @since 2015-11-25
 */
@Service
public class AccessTokenCache extends BaseCache {

    public static final String ACCESS_TOKEN_KEY = "access_token_key";

    public static final RedisKeys.LiveTime expireTime = RedisKeys.LiveTime.DAYS_365;


    /**
     * 把token放入到缓存中
     *
     * @param uid 用户编号
     * @param accessToken token
     */
    public void pushToken(Integer uid, String accessToken) {
        redisService.set(ACCESS_TOKEN_KEY, accessToken, uid+"");
    }

    /**
     * 从缓存中删除token信息
     *
     * @param accessToken token
     */
    public void removeToken(String accessToken) {
        redisService.delHashKey(ACCESS_TOKEN_KEY,accessToken);
    }

    /**
     * 判断token是否存在
     *
     * @param accessToken token
     * @return 是否存在
     */
    public boolean isTokenExists(String accessToken) {
        String memberId = redisService.get(ACCESS_TOKEN_KEY,accessToken,String.class);
        if(memberId!=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据token查询用户编号
     *
     * @param accessToken token
     * @return 用户编号
     */
    public Integer getUid(String accessToken) {
        String memberId = redisService.get(ACCESS_TOKEN_KEY,accessToken,String.class);
        return Integer.valueOf(memberId);
    }

}
