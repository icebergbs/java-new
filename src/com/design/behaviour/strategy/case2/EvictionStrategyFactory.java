package com.design.behaviour.strategy.case2;

import com.design.behaviour.strategy.case1.ConcreteStrategyA;
import com.design.behaviour.strategy.case1.ConcreteStrategyB;
import com.design.behaviour.strategy.case1.Strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingshan
 * @date 2022/8/23 15:37
 */
public class EvictionStrategyFactory {

    private static final Map strategies = new HashMap<>();

    static {
        strategies.put("Fifo", new FifoEvictionStrategy());
        strategies.put("Lfu", new LfuEvictionStrategy());
        strategies.put("Lru", new LruEvictionStrategy());
    }

    public static Strategy getEvictionStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return (Strategy) strategies.get(type);
    }
}
