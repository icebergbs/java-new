package com.java8.jvm.c2_classloader;

public class T01_CacheLinePadding {
    private static class T {
        //������һ��T��     һ��long���͵�x8���ֽ�
        public volatile long x = 0L;
    }

    //Ū��һ��T��������
    public static T[] arr = new T[2];

    //��̬��ʼ�����֮���ڴ��������������飬 ��������������ָ�����new���Ķ��� �����ֻ��һ��8���ֽ�long����
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    //���ʱ�����������߳�
    public static void main(String[] args) throws InterruptedException {
        //��һ���̣߳�ѭ��һ����Σ� ��xֵ���ϲ����仯�� �պ�����ֵλ��һ�������У�������λ��һ��cpu, �Ǿͻᷢ����һ��cpu�͵ڶ���cpu���ϵĸ��»�����
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(System.nanoTime() - start); //276403900
    }

}
