package com.design.principle.c16;
/**
 *
 *
 * @author  bingshan
 * @date 2022/12/4 11:00
 */
public abstract class AlertHandler {
    protected AlertRule rule;
    protected Notification notification;
    public AlertHandler(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }
    public abstract void check(ApiStatInfo apiStatInfo);
}
