package com.design.behaviour.state;

public class ApplicationDemo3 {
    public static void main(String[] args) {
        MarioStateMachine3 mario = new MarioStateMachine3();
        mario.getCurrentState().obtainMushRoom(mario);
        mario.getCurrentState().obtainCape(mario);
        int score = mario.getScore();
        State state = mario.getCurrentState().getName();
        System.out.println("mario score: " + score + "; state: " + state);

    }
}
