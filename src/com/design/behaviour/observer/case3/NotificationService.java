package com.design.behaviour.observer.case3;

public class NotificationService {

    void sendInboxMessage(final long userId, String message){
        System.out.println("Event NotificationService: + userId= " + userId + " message=" + message);
    }
}
