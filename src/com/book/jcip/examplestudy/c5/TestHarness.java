package com.book.jcip.examplestudy.c5;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

public class TestHarness {

    //闭锁是一种同步工具类,可以延迟线程的进度直到其到达终止状态
    public static void main(String[] args) throws InterruptedException {

        timeTasks(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("Test Harness");
            }
        });
    }

    public static void timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        //两个闭锁: 分别表示"起始门" 和 "结束门"
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        System.out.println("time" + (end - start));
    }
}
