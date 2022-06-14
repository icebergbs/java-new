package com.book.easycoding.thread.threallocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class M001_DirtyDataInThreadLocal {

    //ThreadLocal ������
    // 1 ������
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //ʹ�ù̶���СΪ1���̳߳ء� ˵����һ�����߳����Իᱻ��һ���߳����Ը���
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 2; i++) {
            MyThread thread = new MyThread();
            pool.execute(thread);

        }
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                //��һ���߳�set�� ��û�н���remove
                //���ڶ����߳�����ĳ��ԭ��û�н���set����
                threadLocal.set(this.getName() + ", session info.");
                flag = false;
            }

            System.out.println(this.getName() + " �߳���" + threadLocal.get());
        }
    }
}
