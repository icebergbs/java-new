package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "�����");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 r1 = new T05_ReentrantLock5();
        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r1);
        th1.start();
        th2.start();
    }
}
