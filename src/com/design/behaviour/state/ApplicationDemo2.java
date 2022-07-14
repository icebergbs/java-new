package com.design.behaviour.state;

public class ApplicationDemo2 {
    public static void main(String[] args) {
        MarioStateMachine2 mario = new MarioStateMachine2();
        mario.getCurrentState().obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState().getName();
        System.out.println("mario score: " + score + "; state: " + state);

    }
}
