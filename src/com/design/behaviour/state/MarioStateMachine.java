package com.design.behaviour.state;

/**
 * 状态机实现方式一：分支逻辑法
 */
public class MarioStateMachine {
    private int score;

    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        if (this.currentState == State.SMALL) {
            this.score += 100;
            this.currentState = State.SUPER;
        }
    }

    public void obtainCape() {
        if (this.currentState == State.SMALL || this.currentState == State.SUPER) {
            this.score += 200;
            this.currentState = State.CAPE;
        }
    }

    public void obtainFireFlower() {
        if (this.currentState == State.SMALL || this.currentState == State.SUPER) {
            this.score += 300;
            this.currentState = State.FIRE;
        }
    }

    public void meetMonster() {
        if (this.currentState == State.SUPER) {
            this.score -= 100;
            this.currentState = State.SMALL;
        }

        if (this.currentState == State.CAPE) {
            this.score -= 200;
            this.currentState = State.SMALL;
        }

        if (this.currentState == State.FIRE) {
            this.score -= 300;
            this.currentState = State.SMALL;
        }
    }

    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }
}
