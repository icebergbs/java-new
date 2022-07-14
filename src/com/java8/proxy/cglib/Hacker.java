package com.java8.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 实现了方法拦截器接口
 *
 * @author bingshan
 * @date 2021/12/21 16:03
 */
public class Hacker implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("**** I am a hacker, Let's see what the poor programmer is doing Now...");
        methodProxy.invokeSuper(o, objects);
        System.out.println("**** Oh,what a poor programmer......");
        return null;
    }
}
