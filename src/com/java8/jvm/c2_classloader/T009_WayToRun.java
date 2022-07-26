package com.java8.jvm.c2_classloader;

public class T009_WayToRun {

    public static void main(String[] args) {
        //第一次执行了10w遍, 这个就是告诉jvm 虚拟机
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++)
            m();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        //第二次执行了10w遍, 这个进行了编译
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            m();
        }
        //计时间
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

    }

    //执行上面代码
    public static void m() {
        for (long i = 0; i < 10_000; i++) {
            long j = i % 3;
        }
    }
}
