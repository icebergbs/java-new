package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.Semaphore;

public class T11_TestSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(2000);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(2000);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }).start();
    }
}
