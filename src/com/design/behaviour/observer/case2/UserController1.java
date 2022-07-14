package com.design.behaviour.observer.case2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class UserController1 {

    private static UserService userService = new UserService(); //依赖注入
    private static List<RegObserver> regObservers = new ArrayList<>();

    private static Executor executor;

    //一次性设置好,子后也不可能动态的修改
    public static void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }


    /**
     * 对于异步非阻塞观察者模式，如果只是实现一个简易版本，不考虑任何通用性、复用性，实际上是非常容易的。
     *
     * 其中一种是：在每个 handleRegSuccess() 函数中创建一个新的线程执行代码逻辑；
     * 另一种是：在 UserController 的 register() 函数中使用线程池来执行每个观察者的 handleRegSuccess() 函数
     *
     * 对于第一种实现方式，频繁地创建和销毁线程比较耗时，并且并发线程数无法控制，创建过多的线程会导致堆栈溢出。
     * 第二种实现方式，尽管利用了线程池解决了第一种实现方式的问题，
     * 但线程池、异步执行逻辑都耦合在了 register() 函数中，增加了这部分业务代码的维护成本。
     * @param telephone
     * @param password
     * @return
     */
    public static Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        for (RegObserver  observer : regObservers) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observer.handleRegSuccess(userId);
                }
            });
        }
        return userId;
    }
}




