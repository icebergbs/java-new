package com.design.behaviour.observer.case4;

import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 表示 @Subscribe注解的方法, target表示观察者类, method表示方法; 主要用在ObserverRegistry观察者注册表中
 */
public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) { //event是method方法的参数
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
