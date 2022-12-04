package com.design.principle.c16;

/**
 * @author bingshan
 * @date 2022/12/4 11:48
 */
public class ApplicationContext1 {
    private AlertRule alertRule;
    private Notification notification;
    private Alert1 alert1;

    public void initializeBeans() {
        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
        alert1 = new Alert1();
        // 改动三：注册handler
        alert1.addAlertHandler(new TimeoutAlertHandler1(alertRule, notification));
    }
    //...省略其他未改动代码...
    public Alert1 getAlert() { return alert1; }

    // 饿汉式单例
    private static final ApplicationContext1 instance = new ApplicationContext1();
    private ApplicationContext1() {
        initializeBeans();
    }
    public static ApplicationContext1 getInstance() {
        return instance;
    }
}
