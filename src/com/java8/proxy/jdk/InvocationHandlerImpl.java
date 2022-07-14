package com.java8.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author bingshan
 * @date 2021/12/21 14:37
 */
public class InvocationHandlerImpl implements InvocationHandler {

    private ElectricCar car;

    public InvocationHandlerImpl(ElectricCar car) {
        this.car = car;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("You are going to invoke " + method.getName() + " ...");
        method.invoke(car, null);
        System.out.println(method.getName() + " invocation Has Been finished...");
        return null;
    }
}
