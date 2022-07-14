package com.design.behaviour.state;

public enum Event1 {

    GOT_MUSHROOM(0),
    GOT_CAPE(1),
    GOT_FIRE(2),
    MET_MONSTER(3);
    private int value;


    Event1(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
