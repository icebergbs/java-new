package com.design.behaviour.observer.case2;

public class RegPromotionObserver implements RegObserver{

    private PromotionService promotionService = new PromotionService(); // 依赖注入

    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }

    public void handleRegSuccess1(long userId) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();

    }
}
