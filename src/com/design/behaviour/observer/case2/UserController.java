package com.design.behaviour.observer.case2;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    /**
     * 如果需求频繁变动，比如，用户注册成功之后，不再发放体验金，而是改为发放优惠券，并且还要给用户发送一封“欢迎注册成功”的站内信。
     * 这种情况下，我们就需要频繁地修改 register() 函数中的代码，违反开闭原则。
     * 而且，如果注册成功之后需要执行的后续操作越来越多，那 register() 函数的逻辑会变得越来越复杂，也就影响到代码的可读性和可维护性。
     * 这个时候，观察者模式就能派上用场了
     */

    private static UserService userService = new UserService(); //依赖注入
    private static List<RegObserver> regObservers = new ArrayList<>();

    //一次性设置好,子后也不可能动态的修改
    public static void setRegObservers(List<RegObserver> observers) {
        regObservers.addAll(observers);
    }

    /**
     * 它是一种同步阻塞的实现方式。观察者和被观察者代码在同一个线程内执行，被观察者一直阻塞，
     * 直到所有的观察者代码都执行完成之后，才执行后续的代码。
     * 对照上面讲到的用户注册的例子，register() 函数依次调用执行每个观察者的 handleRegSuccess() 函数，
     * 等到都执行完成之后，才会返回结果给客户端。
     * @param telephone
     * @param password
     * @return
     */
    public static Long register(String telephone, String password) {
        //省略输入参数的校验代码
        //省略userService.register()异常的try-catch代码
        long userId = userService.register(telephone, password);

        for (RegObserver  observer : regObservers) {
            observer.handleRegSuccess(userId);
        }
        return userId;
    }


    public static void main(String[] args) {
        ArrayList<RegObserver> arr = new ArrayList<>();
        /**
         * 当我们需要添加新的观察者的时候，比如，用户注册成功之后，推送用户注册信息给大数据征信系统，
         * 基于观察者模式的代码实现，UserController 类的 register() 函数完全不需要修改，
         * 只需要再添加一个实现了 RegObserver 接口的类，并且通过 setRegObservers() 函数将它注册到 UserController 类中即可。
         *
         * 不过，你可能会说，当我们把发送体验金替换为发送优惠券的时候，
         * 需要修改 RegPromotionObserver 类中 handleRegSuccess() 函数的代码，这还是违反开闭原则呀？
         * 你说得没错，不过，相对于 register() 函数来说，handleRegSuccess() 函数的逻辑要简单很多，修改更不容易出错，
         * 引入 bug 的风险更低。
         */
        RegNotificationObserver regNotificationObserver = new RegNotificationObserver();
        RegPromotionObserver regPromotionObserver = new RegPromotionObserver();
        arr.add(regNotificationObserver);
        arr.add(regPromotionObserver);
        setRegObservers(arr);

        register("12111", "123456");
    }
}




