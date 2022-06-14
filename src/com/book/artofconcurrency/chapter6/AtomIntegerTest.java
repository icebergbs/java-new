package com.book.artofconcurrency.chapter6;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomIntegerTest {

    /**
     * Atomic包里一共提供了13个类,属于4种类型的原子更新方式
     * 原子更新基本类型,原子更新数组,原子更新引用和原子更新属性
     * 基本都是使用Unsafe实现的包装类
     */


    static AtomicInteger ai = new AtomicInteger(1);


    /**
     * 数组value通过构造方法传递进去,
     * AIA会将当前数组复制一份,所以AIA对内部的数组元素进行修改时,不会影响传入的数组
     */
    static int[] value = new int[]{1, 2, 3};
    static AtomicIntegerArray aia = new AtomicIntegerArray(value);


    /**
     * 原子更新引用类型,
     */
    static AtomicReference<User> userAtomicReference = new AtomicReference<>();


    /**
     * 原子更新字段类
     * 原子地更新某个类里的某个字段
     * <p>
     * 原子更新字段类需要两步:
     * 1. 因为原子更新字段类都是抽象类,每次使用必须使用静态方法newUpdater()创建一个更新容器,并设置想要更新的类和属性
     * 2. 更新类的字段必须使用public volatile修饰符
     */
    private static AtomicIntegerFieldUpdater<User1> aifu = AtomicIntegerFieldUpdater.newUpdater(User1.class, "old");


    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
        System.out.println("---------");

        aia.getAndSet(0, 3);
        System.out.println(aia.get(0));
        System.out.println(value[0]);
        System.out.println("---------");


        User user = new User("conan", 15);
        userAtomicReference.set(user);
        User updateUser = new User("shinichi", 17);
        userAtomicReference.compareAndSet(user, updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());
        System.out.println("---------");


        //年龄10
        User1 conan = new User1("conan", 10);
        //张了一岁,但仍返回旧的年龄
        System.out.println(aifu.getAndIncrement(conan));
        //输出现在的年龄
        System.out.println(aifu.get(conan));
    }


    public static class User1 {

        private String name;
        public volatile int old;

        public User1(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }

}
