package com.design.principle.c16;

/**
 * @author bingshan
 * @date 2022/12/4 11:43
 */
// 改动二：添加新的handler
public class TimeoutAlertHandler1 extends AlertHandler1 {
    public TimeoutAlertHandler1(AlertRule rule, Notification notification) {
        super(rule, notification);
    }

    @Override
    public void check(ApiStatInfo1 apiStatInfo1) {
        long timeout = apiStatInfo1.getTimeoutCount();
        if (timeout > 3000L) {
            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
        }
    }}
