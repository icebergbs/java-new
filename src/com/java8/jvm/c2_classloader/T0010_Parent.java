package com.java8.jvm.c2_classloader;

public class T0010_Parent {

    private static T006_MSBClassLoader parent = new T006_MSBClassLoader();

    private static class MyLoader extends ClassLoader {
        public MyLoader() {
            super(parent);
        }
    }
}
