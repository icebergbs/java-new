package com.java8.juc.mishiti;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producter = lock.newCondition();
    private Condition consumer = lock.newCondition();

    //������
    public synchronized void put(T t) {
        try {
            lock.lock();
            while (lists.size() == MAX) {
                try {
                    producter.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            lists.add(t);
            ++count;
            consumer.signalAll();
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }

    }

    //������
    public synchronized T get() {
        T t = null;
        try {
            lock.lock();
            while (lists.size() == 0) {
                try {
                    consumer.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            t = lists.removeFirst();
            count--;
            producter.signalAll();
        } finally {
            lock.unlock();
        }

        return t;
    }

    public static void main(String[] args) {
        MyContainer<String> c = new MyContainer<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "P" + i).start();
        }
    }
}
