package com.design.behaviour.observer.case1;

public class Demo {

    public static void main(String[] args) {
        /**
         * 观察者模式的 "模板代码"
         */
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObservers(new Message());
    }
}
