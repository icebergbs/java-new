package com.design.behaviour.state;

import static com.design.behaviour.state.State.*;

public class MarioStateMachine1 {

    private int score;
    private State currentState;

    private static final State[][] transitionTable = {
            {SUPER, CAPE, FIRE, SMALL},
            {SUPER, CAPE, FIRE, SMALL},
            {CAPE, CAPE, CAPE, SMALL},
            {FIRE, FIRE, FIRE, SMALL}
    };

    private static final int[][] actionTable = {
            {+100, +200, +300, +0},
            {+0, +200, +300, -100},
            {+0, +0, +0, -200},
            {+0, +0, +0, -300}
    };

    public MarioStateMachine1() {
        this.score = 0;
        this.currentState = SMALL;
    }

    public void obtainMushRoom() {
        executeEvent(Event1.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(Event1.GOT_CAPE);
    }

    public void obtainFireFlower() {
        executeEvent(Event1.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(Event1.MET_MONSTER);
    }


    private void executeEvent(Event1 event1) {
        int stateValue = currentState.getValue();
        int eventValue = event1.getValue();
        this.currentState = transitionTable[stateValue][eventValue];
        this.score += actionTable[stateValue][eventValue];
    }


    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }
}
