package com.wangguang.services.cache;

import com.wangguang.services.RedisService;
import org.springframework.beans.factory.annotation.Autowired;


abstract class BaseCache {

    @Autowired
    protected RedisService redisService;


}
