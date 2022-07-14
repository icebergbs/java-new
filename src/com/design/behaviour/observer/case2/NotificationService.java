package com.design.behaviour.observer.case2;

public class NotificationService {

    void sendInboxMessage(final long userId, String message){
        System.out.println("NotificationService: + userId= " + userId + " message=" + message);
    }
}
