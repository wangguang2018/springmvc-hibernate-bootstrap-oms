package com.wangguang.core.machine;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 全局缓存
 *
 * @author Demon
 * @since 12/03/2018
 */
public class MachineGlobalCache {

    /**
     * 机器服务map
     */
    public static final Map<String, String> machineServiceMap = Maps.newConcurrentMap();
}
