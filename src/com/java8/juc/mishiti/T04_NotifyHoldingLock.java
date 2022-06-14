package com.java8.juc.mishiti;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyHoldingLock {

    //���volatile, ʹt2�ܹ��õ�֪ͨ
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyHoldingLock c = new T04_NotifyHoldingLock();
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
                //֪ͨ t1����ִ��
                lock.notifyAll();
            }
            //Exception
//				//֪ͨ t1����ִ��
//				lock.notifyAll();

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
                        lock.notifyAll();
                        //�ͷ�������t2����ִ��
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
