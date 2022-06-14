package com.book.artofconcurrency.chapter5;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class FairAndUnfairTest {

    private static ReentrantLock2 fairLock = new ReentrantLock2(true);
    private static ReentrantLock2 unfairLock = new ReentrantLock2(false);


    public static void main(String[] args) throws InterruptedException {
        int j;
        int i = j = 3;
        System.out.println();
        testLock();
    }


    private static void testLock() throws InterruptedException {
        //启动5个Job
        for (int i = 0; i < 5; i++) {
            new Job(fairLock).start();
        }
        Thread.sleep(3000L);
        System.out.println();
        for (int i = 0; i < 5; i++) {
            new Job(unfairLock).start();
        }

    }

    private static class Job extends Thread {
        private ReentrantLock2 lock;

        public Job(ReentrantLock2 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            //连续两次打印当前的Thread  和等待队列中的Thread
            try {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
            System.out.println("Lock By [" + Thread.currentThread().getName() + "], Waiting by [ " + lock.getQueuedThreads() + "]");

            lock.lock();
            try {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }
            System.out.println("Lock By [" + currentThread().getName() + "], Waiting by [ " + lock.getQueuedThreads() + "]");
        }
    }

    private static class ReentrantLock2 extends ReentrantLock {
        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThreads() {
            List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }


}
