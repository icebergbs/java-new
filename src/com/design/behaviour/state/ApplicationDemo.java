package com.design.behaviour.state;

public class ApplicationDemo {

    public static void main(String[] args) {
        MarioStateMachine mario = new MarioStateMachine();
        mario.obtainMushRoom();
        mario.obtainMushRoom();
        mario.obtainCape();
        mario.meetMonster();
        int score = mario.getScore();
        State state = mario.getCurrentState();
        System.out.println("mario score: " + score + "; state: " + state);

    }
}
