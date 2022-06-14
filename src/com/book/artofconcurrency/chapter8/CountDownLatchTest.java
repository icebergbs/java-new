package com.book.artofconcurrency.chapter8;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    //一个线程调用countDown()方法happen-before, 另外一个线程调用await方法
    static CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                cdl.countDown();
                System.out.println(2);
                cdl.countDown();
            }
        }).start();
        cdl.await();
        System.out.println(3);
    }
}
