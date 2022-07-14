package com.design.behaviour.state;

public interface IMario2 {
    State getName();

    //事件定义
    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();
}
