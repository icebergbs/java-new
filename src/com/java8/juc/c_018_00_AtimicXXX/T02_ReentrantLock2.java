package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock2 {

    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();

        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    void m2() {
        try {
            lock.lock();
            System.out.println("m2...");
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        T02_ReentrantLock2 r1 = new T02_ReentrantLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }

}
