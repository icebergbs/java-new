package com.design.behaviour.observer.case1;



public class ConcreteObserverTwo implements Observer{
    @Override
    public void update(Message message) {
        //TODO: 获取消息通知, 执行自己的逻辑
        System.out.println("ConcreteObserver Two is notified.");
    }
}
