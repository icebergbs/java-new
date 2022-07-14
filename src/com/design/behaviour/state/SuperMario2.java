package com.design.behaviour.state;

public class SuperMario2 implements IMario2 {

    private MarioStateMachine2 stateMachine2;

    public SuperMario2(MarioStateMachine2 stateMachine2) {
        this.stateMachine2 = stateMachine2;
    }

    @Override
    public State getName() {
        return State.SUPER;
    }

    @Override
    public void obtainMushRoom() {
        //do nothing...
    }

    @Override
    public void obtainCape() {
        stateMachine2.setCurrentState(new CapeMario2(stateMachine2));
        stateMachine2.setScore(stateMachine2.getScore() + 200);
    }

    @Override
    public void obtainFireFlower() {
        stateMachine2.setCurrentState(new FireMario2(stateMachine2));
        stateMachine2.setScore(stateMachine2.getScore() + 300);
    }

    @Override
    public void meetMonster() {
        stateMachine2.setCurrentState(new SmallMario2(stateMachine2));
        stateMachine2.setScore(stateMachine2.getScore() - 100);
    }
}
