package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () ->
                System.out.println("����20"));

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {

                    barrier.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
