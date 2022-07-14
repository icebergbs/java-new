package com.design.behaviour.observer.case2;

public class RegNotificationObserver implements RegObserver{

    private NotificationService notificationService = new NotificationService();

    @Override
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome....");
    }
}
