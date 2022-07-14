package com.design.behaviour.state;

public class MarioStateMachine2 {
    private int score;

    private IMario2 currentState; //不在使用枚举表示状态

    public MarioStateMachine2() {
        this.score = 0;
        this.currentState = new SmallMario2(this);
    }

    public int getScore() {
        return score;
    }

    public IMario2 getCurrentState() {
        return currentState;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario2 currentState) {
        this.currentState = currentState;
    }
}
