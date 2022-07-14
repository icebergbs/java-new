package com.design.behaviour.observer.case1;




public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(Message message);
}
