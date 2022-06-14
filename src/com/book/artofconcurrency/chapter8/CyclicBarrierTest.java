package com.book.artofconcurrency.chapter8;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    //static CyclicBarrier c = new CyclicBarrier(2);

    //在线程到达屏障时,优先执行barrierAction
    static CyclicBarrier c = new CyclicBarrier(2, new A());

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();

        try {
            c.await();
        } catch (Exception e) {

        }
        System.out.println(2);
    }

    static class A implements Runnable {
        @Override
        public void run() {
            System.out.println(3);
        }
    }


}
