package com.java8.jvm.c2_classloader;

/**
 * 自定义指定父亲的 classloader
 */
public class T0010_Parent {

    private static T006_MSBClassLoader parent = new T006_MSBClassLoader();

    /**
     * 自定义classloader从 ClassLoader里继承, 构造方法里面调用 super指定parent
     */
    private static class MyLoader extends ClassLoader {
        public MyLoader() {
            super(parent);
        }
    }
}
