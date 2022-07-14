package com.design.behaviour.state;

public class FireMario3 implements IMario3 {

    private static final FireMario3 instance = new FireMario3();

    private FireMario3() {
    }

    ;

    public static FireMario3 getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.FIRE;
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
        //do nothing...
    }

    @Override
    public void meetMonster(MarioStateMachine3 stateMachine3) {
        stateMachine3.setCurrentState(SmallMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() - 300);
    }
}
