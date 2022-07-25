package com.java8.jvm.c2_classloader;

import java.util.Arrays;

public class T004_ParentAndChild {

    /**
     * 父加载器不是 "类加载器的加载器" 也不是 "类加载器的父类加载器"
     * @param args
     */
    public static void main(String[] args) {
        /**
         * sun.misc.Launcher$AppClassLoader@18b4aac2
         *
         *      sun -> misc 包下面的Launcher类, 这个类有一个内部类叫 ExeClassLoader
         */
        //appClassLoader
        System.out.println(T004_ParentAndChild.class.getClassLoader());
        //null值
        System.out.println(T004_ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        //extClassLoader
        System.out.println(T004_ParentAndChild.class.getClassLoader().getParent());
        //null值
        System.out.println(T004_ParentAndChild.class.getClassLoader().getParent().getParent());
        //空指针
        System.out.println(T004_ParentAndChild.class.getClassLoader().getParent().getParent().getParent());
    }
}
