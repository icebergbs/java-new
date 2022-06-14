package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.CountDownLatch;

public class T06_TestCountDownLatch {

    public static void main(String[] args) {
        usingCountDownLatch();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                latch.countDown(); //��һ
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        try {
            //��˨
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");

    }
}
