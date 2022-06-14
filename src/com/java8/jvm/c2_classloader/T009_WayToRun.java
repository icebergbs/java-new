package com.java8.jvm.c2_classloader;

public class T009_WayToRun {

    public static void main(String[] args) {
        //��һ��ִ����10��飬 ����jvm�����
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
            m();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //�ڶ���ִ����10��飬 ��������˱���
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            m();
        }
        //��ʱ��
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }

    public static void m() {
        for (long i = 0; i < 10_000; i++) {
            long j = i % 3;
        }
    }
}
