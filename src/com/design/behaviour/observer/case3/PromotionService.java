package com.design.behaviour.observer.case3;

public class PromotionService {

    void issueNewUserExperienceCash(final long userId){
        System.out.println("Event PromotionService: userId=" + userId);
    }
}
