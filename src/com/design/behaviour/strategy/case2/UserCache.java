package com.design.behaviour.strategy.case2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingshan
 * @date 2022/8/23 15:35
 */
public class UserCache {

    private Map cacheData = new HashMap<>();
    private EvictionStrategy eviction;
    public UserCache(EvictionStrategy eviction) {
        this.eviction = eviction;
    } //...
}
