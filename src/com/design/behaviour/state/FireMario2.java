package com.design.behaviour.state;

public class FireMario2 implements IMario2 {

    private MarioStateMachine2 stateMachine2;

    public FireMario2(MarioStateMachine2 stateMachine2) {
        this.stateMachine2 = stateMachine2;
    }

    @Override
    public State getName() {
        return State.FIRE;
    }

    @Override
    public void obtainMushRoom() {
        //do nothing...
    }

    @Override
    public void obtainCape() {
        //do nothing...
    }

    @Override
    public void obtainFireFlower() {
        //do nothing...
    }

    @Override
    public void meetMonster() {
        stateMachine2.setCurrentState(new SmallMario2(stateMachine2));
        stateMachine2.setScore(stateMachine2.getScore() - 300);
    }
}
