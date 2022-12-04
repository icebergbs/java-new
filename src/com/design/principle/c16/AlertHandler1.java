package com.design.principle.c16;
/**
 *
 *
 * @author  bingshan
 * @date 2022/12/4 11:00
 */
//代码未改动...
public abstract class AlertHandler1 {
    protected AlertRule rule;
    protected Notification notification;
    public AlertHandler1(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }
    public abstract void check(ApiStatInfo1 apiStatInfo1);
}
