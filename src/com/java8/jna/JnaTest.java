package com.java8.jna;



public class JnaTest {

    public static void main(String[] args) {
        testVixHz_InitSDK();
    }

    /**
     * 初始化SDK
     */
    public static void testVixHz_InitSDK() {
        int sum = CLibrary.INSTANCE.add(1, 2);
        System.out.println("1+2= " + sum);
        System.out.println("hello world");

        U8LoginLibrary.U8_INSTANCE.clsLogin();

    }
}




