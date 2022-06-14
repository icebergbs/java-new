package com.java8.jvm.c2_classloader;

public class T005_LoadClassByHand {

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = T005_LoadClassByHand.class.getClassLoader().loadClass("com.java8.jvm.c2_classloader.T003_ClassLocderScope");
        System.out.println(clazz.getName());
    }
}
