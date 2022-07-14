package com.design.behaviour.state;

public class SmallMario3 implements IMario3 {

    private static final SmallMario3 instance = new SmallMario3();

    private SmallMario3() {
    }

    ;

    public static SmallMario3 getInstance() {
        return instance;
    }


    @Override
    public State getName() {
        return State.SMALL;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 stateMachine3) {
        stateMachine3.setCurrentState(SuperMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() + 100);
    }

    @Override
    public void obtainCape(MarioStateMachine3 stateMachine3) {
        stateMachine3.setCurrentState(CapeMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() + 200);
    }

    @Override
    public void obtainFireFlower(MarioStateMachine3 stateMachine3) {
        stateMachine3.setCurrentState(FireMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() + 300);
    }

    @Override
    public void meetMonster(MarioStateMachine3 stateMachine3) {
        //do nothing...
    }
}
