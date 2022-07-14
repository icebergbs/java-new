package com.design.behaviour.state;

public class ApplicationDemo1 {
    public static void main(String[] args) {
        MarioStateMachine1 mario = new MarioStateMachine1();
        mario.obtainMushRoom();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);

    }
}
