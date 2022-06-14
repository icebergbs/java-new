package com.book.easycoding.thread.threallocal;

import java.util.concurrent.ThreadLocalRandom;

public class M001_CsGameByThreadLocal {
    private static final Integer BULLET_NUMBER = 1500;
    private static final Integer KILLED_ENEMIES = 0;
    private static final Integer LIFE_VALUE = 10;
    private static final Integer TOTAL_PLAYERS = 10;

    //���������չʾÿ������Ĳ�ͬ������
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    //��ʼ���ӵ���
    private static final ThreadLocal<Integer> BULLET_NUMBER_THREADLOCAL = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return KILLED_ENEMIES;
        }

        ;
    };
    //��ʼ��ɱ����
    private static final ThreadLocal<Integer> KILLED_ENEMIES_THREADLOCAL = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return BULLET_NUMBER;
        }

        ;
    };
    //��ʼ���Լ�������
    private static final ThreadLocal<Integer> LIFE_VALUE_THREADLOCAL = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return LIFE_VALUE;
        }

        ;
    };

    private static class Player extends Thread {
        @Override
        public void run() {
            Integer bullets = BULLET_NUMBER_THREADLOCAL.get() - RANDOM.nextInt(BULLET_NUMBER);
            Integer killEnemies = KILLED_ENEMIES_THREADLOCAL.get() + RANDOM.nextInt(TOTAL_PLAYERS / 2);
            Integer lifeValue = LIFE_VALUE_THREADLOCAL.get() - RANDOM.nextInt(LIFE_VALUE);
            System.out.println(getName() + ", BULLET_NUMBER is " + bullets);
            System.out.println(getName() + ", KILLED_ENEMIES is " + killEnemies);
            System.out.println(getName() + ", LIFE_VALUE is " + lifeValue + "\n");

            BULLET_NUMBER_THREADLOCAL.remove();
            LIFE_VALUE_THREADLOCAL.remove();
            KILLED_ENEMIES_THREADLOCAL.remove();
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_PLAYERS; i++) {
            new Player().start();
        }
    }

}
