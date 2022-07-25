package com.java8.jvm.c2_classloader;

/**
 * 类加载器范围
 *      sun.boot.class.path
 *          -Bootstrap ClassLoader加载路径
 *      java.ext.dirs
 *          -ExtensionClassLoader 加载路径
 *      java.class.path
 *          -AppClassLoader 加载路径
 */
public class T003_ClassLocderScope {

    public static void main(String[] args) {
        /**
         * Launcher 加载路径
         *      首先 pathBoot拿到属性
         *      把字符串的分号替换成换行符
         */

        //ClassLoader
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(":", System.lineSeparator()));
        System.out.println("Ext-------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(":", System.lineSeparator()));
        System.out.println("Application-------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(":", System.lineSeparator()));
    }
}
