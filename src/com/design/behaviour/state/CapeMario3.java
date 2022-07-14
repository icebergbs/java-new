package com.design.behaviour.state;

public class CapeMario3 implements IMario3 {

    private static final CapeMario3 instance = new CapeMario3();

    private CapeMario3() {
    }

    public static CapeMario3 getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.CAPE;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 stateMachine3) {
        //do nothing...
    }

    @Override
    public void obtainCape(MarioStateMachine3 stateMachine3) {
        //do nothing...
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 stateMachine3) {
        //do nothing
    }

    @Override
    public void meetMonster(MarioStateMachine3 stateMachine3) {
        stateMachine3.setCurrentState(SmallMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() - 200);
    }
}
