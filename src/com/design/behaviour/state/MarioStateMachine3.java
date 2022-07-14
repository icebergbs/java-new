package com.design.behaviour.state;

public class MarioStateMachine3 {
    private int score;

    private IMario3 currentState; //不在使用枚举表示状态

    public MarioStateMachine3() {
        this.score = 0;
        this.currentState = SmallMario3.getInstance();
    }

    public int getScore() {
        return score;
    }

    public IMario3 getCurrentState() {
        return currentState;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurrentState(IMario3 currentState) {
        this.currentState = currentState;
    }
}
