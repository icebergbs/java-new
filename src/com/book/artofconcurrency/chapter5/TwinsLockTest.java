package com.book.artofconcurrency.chapter5;


import java.util.concurrent.locks.Lock;

/**
 * 测试用例
 */
public class TwinsLockTest {

    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test() throws InterruptedException {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        //启动 10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }

        //每隔一秒换行
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            System.out.println();
        }
    }
}
