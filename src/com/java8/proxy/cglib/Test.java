package com.java8.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author bingshan
 * @date 2021/12/21 16:27
 */
public class Test {
    public static void main(String[] args) {
        Programmer programmer = new Programmer();

        String paths = programmer.getClass().getResource(".").getPath();
        System.out.println(paths);
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, paths);

        Hacker hacker = new Hacker();
        //cglib中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        //设置要创建动态代理的类
        enhancer.setSuperclass(programmer.getClass());
        //设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack, 而CallBack则需要实行intercept()进行拦截
        enhancer.setCallback(hacker);

        System.out.println("-------");
        Programmer proxy = (Programmer) enhancer.create();
        System.out.println("-------");
        proxy.code();


    }
}
