package com.design.behaviour.strategy.case2;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author bingshan
 * @date 2022/8/23 15:36
 */
// 运行时动态确定，根据配置文件的配置决定使用哪种策略
public class Application {

    public static void main(String[] args) throws Exception {
        EvictionStrategy evictionStrategy = null;
        Properties props = new Properties();
        props.load(new FileInputStream("./config.properties"));
        String type = props.getProperty("eviction_type");
        evictionStrategy = (EvictionStrategy) EvictionStrategyFactory.getEvictionStrategy(type);
        UserCache userCache = new UserCache(evictionStrategy);
        //...
    }
}
