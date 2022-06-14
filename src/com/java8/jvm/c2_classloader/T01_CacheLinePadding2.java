package com.java8.jvm.c2_classloader;


public class T01_CacheLinePadding2 {

    private static class Padding {
        //���� 7��long���͵���
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    //��padding�̳У� �ͻᷢ����ǰ������ռ��56���ֽڣ� Ȼ���һ�����Լ�������浽���棬 ����������Լ���ռ��һ�У� ������һ�����Բ�����һ���������Ч�ʾ͸���
    private static class T extends Padding {
        //������һ��T��     һ��long���͵�x8���ֽ�
        public volatile long x = 0L;
    }

    //Ū��һ��T��������
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    //���ʱ�����������߳�
    public static void main(String[] args) throws InterruptedException {
        //��һ���̣߳�ѭ��һ����Σ� ��xֵ���ϲ����仯�� �պ�����ֵλ��һ�������У���취������λ��һ�������У� ��������ϻ�ȵ�һ��Ч�ʸ�
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
        System.out.println(System.nanoTime() - start); //74803700
    }
}
