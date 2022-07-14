package com.design.behaviour.observer.case4;

import com.google.common.annotations.Beta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解: 用于标明观察者中的那个函数可以接受消息
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Beta
public @interface DefSubscribe {
}
