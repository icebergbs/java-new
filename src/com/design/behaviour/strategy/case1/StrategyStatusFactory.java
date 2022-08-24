package com.design.behaviour.strategy.case1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingshan
 * @date 2022/8/23 15:26
 */

/**
 * 策略的创建：
 *   策略是有状态的， 每次从工厂方法中获取新创建的策略对象
 */
public class StrategyStatusFactory {
    public static Strategy getStrategy(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        } if (type.equals("A")) {
            return new ConcreteStrategyA();
        } else if (type.equals("B")) {
            return new ConcreteStrategyB();
        }
        return null;
    }
}
