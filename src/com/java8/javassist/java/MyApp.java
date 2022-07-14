package com.java8.javassist.java;
/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 19:37
 */
public class MyApp {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println(String.class.getField("hiddenValue").getName());
    }
}
