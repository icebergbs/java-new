package com.design.behaviour.state;

public class SuperMario3 implements IMario3 {

    private static final SuperMario3 instance = new SuperMario3();

    private SuperMario3() {
    }

    ;

    public static SuperMario3 getInstance() {
        return instance;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom(MarioStateMachine3 stateMachine3) {
        //do nothing...
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
        stateMachine3.setCurrentState(SmallMario3.getInstance());
        stateMachine3.setScore(stateMachine3.getScore() - 100);
    }
}
