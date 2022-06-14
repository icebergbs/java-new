package com.book.artofconcurrency.chapter8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    /**
     * Semaphore 用来控制同时访问特定资源的线程数量, 它通过协调各个线程,以保证合理的使用公共资源.
     */

    /**
     * Semaphore可以用于流量控制,特别是公有资源有限的应用场景,比如数据库连接.
     * 假如有一个需求,要读取几万个文件的数据,因为都是IO密集型任务,可以启动几十个线程并发读取,
     * 如果读到内存还需存储到数据库,而数据库连接只有10个,
     * 这时就要控制只有10个线程同时获取数据库连接,否则会报无法获取连接
     */

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        s.acquire();
                        System.out.println(Thread.currentThread().getName() + "  save data");
                        s.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        threadPool.shutdown();
    }
}
