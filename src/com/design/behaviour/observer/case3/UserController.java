package com.design.behaviour.observer.case3;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class UserController {

    private static UserService userService = new UserService();
    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;
    private static EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));


    public UserController () {
        //eventBus = new EventBus(); //同步阻塞模式
        //eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
    }

    public static void serRegObservers(List<Object> observers) {
        for (Object observer : observers) {
            eventBus.register(observer);
        }
    }

    public static Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);
        eventBus.post(userId);
        return userId;
    }

    public static void main(String[] args) {
        ArrayList<Object> arr = new ArrayList<>();
        RegNotificationObserver regNotificationObserver = new RegNotificationObserver();
        RegPromotionObserver regPromotionObserver = new RegPromotionObserver();
        arr.add(regNotificationObserver);
        arr.add(regPromotionObserver);
        serRegObservers(arr);

        register("121", "12345678");
    }
}
