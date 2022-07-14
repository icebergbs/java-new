package com.java8.proxy.cglib;

/**
 * 程序猿类
 *
 * @author bingshan
 * @date 2021/12/21 16:00
 */
public class Programmer {

    //public
    public void code() {
        System.out.println("I'm a Programmer,Just Coding.....");
    }

    //default
    void go() {
        System.out.println("car go no xiushi");
    }

    //protected
    protected void test() {
        System.out.println("car test protected");
    }
}
