package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAddr {

    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong();
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        Thread[] threads = new Thread[1];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
                }
            });
        }

        long start = System.currentTimeMillis();

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Atomic: " + count1.get() + "time" + (end - start));


        Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(new Runnable() {

                        @Override
                        public void run() {
                            for (int j = 0; j < 100000; j++) {
                                synchronized (lock) {
                                    count2++;
                                }
                            }

                        }
                    });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        end = System.currentTimeMillis();
        System.out.println("Sync: " + count2 + "time" + (end - start));

        for (int i = 0; i < threads.length; i++) {
            threads[i] =
                    new Thread(() -> {
                        for (int j = 0; j < 100000; j++) {
                            count3.increment();
                        }
                    });
        }

        start = System.currentTimeMillis();
        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            try {
                t.join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        end = System.currentTimeMillis();
        System.out.println("LongAdder: " + count3.longValue() + "time" + (end - start));
    }
}
