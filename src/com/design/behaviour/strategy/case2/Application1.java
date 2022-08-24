package com.design.behaviour.strategy.case2;

/**
 * @author bingshan
 * @date 2022/8/23 15:41
 */

// 非运行时动态确定，在代码中指定使用哪种策略
public class Application1 {
    public static void main(String[] args) {
        //...
        EvictionStrategy evictionStrategy = new LruEvictionStrategy();
        UserCache userCache = new UserCache(evictionStrategy);
        //...
    }
}
