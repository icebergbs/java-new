package com.design.behaviour.observer.case3;


import com.google.common.eventbus.Subscribe;

public class RegNotificationObserver{

    private NotificationService notificationService = new NotificationService();

    @Subscribe
    public void handleRegSuccess(Long userId) {
        notificationService.sendInboxMessage(userId, "Welcome....");
    }
}
