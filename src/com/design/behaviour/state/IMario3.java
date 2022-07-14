package com.design.behaviour.state;

public interface IMario3 {
    State getName();

    //事件定义
    void obtainMushRoom(MarioStateMachine3 stateMachine3);

    void obtainCape(MarioStateMachine3 stateMachine3);

    void obtainFireFlower(MarioStateMachine3 stateMachine3);

    void meetMonster(MarioStateMachine3 stateMachine3);
}
