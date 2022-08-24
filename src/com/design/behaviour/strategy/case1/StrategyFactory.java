package com.design.behaviour.strategy.case1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingshan
 * @date 2022/8/23 15:26
 */

/**
 * 策略的创建：
 *   策略是无状态的， 每次都是同一个
 */
public class StrategyFactory {
    private static final Map strategies = new HashMap<>();

    static {
        strategies.put("A", new ConcreteStrategyA());
        strategies.put("B", new ConcreteStrategyB());
    }

    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return (Strategy) strategies.get(type);
    }
}
