package com.java8.juc.c_018_00_AtimicXXX;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport2 {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    //ʹ��LockSupport�� park() ����������ǰ�߳�t
                    LockSupport.park();
                }
                if (i == 8) {
                    //ʹ��LockSupport�� park() ����������ǰ�߳�t
                    LockSupport.park();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;

            }
        });
        t.start();

        //�����߳�t
        LockSupport.unpark(t);
        //���߳�t ��û�б�������ʱ�� �Ѿ������� LockSupport��unpark()����������t
        //�Ժ�ŵ���LockSupport��park()��ʹ�߳�t��������t��û�б�����
        //�д˿��Կ��� LockSupport��unpark()������������LockSupport��park()����ִ��
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;
        LockSupport.unpark(t);
    }
}
