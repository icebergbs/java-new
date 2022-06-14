package com.java8.juc.mishiti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T03_NotifyHoldingLock {

    //���volatile, ʹt2�ܹ��õ�֪ͨ
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 ����");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 ����");
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 ����");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
