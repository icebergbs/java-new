package com.java8.jvm.c2_classloader;

public class T003_ClassLocderScope {

    public static void main(String[] args) {
        //Launcher
        //ClassLoader
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));
        System.out.println("Ext-------------");
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.print(pathExt.replaceAll(";", System.lineSeparator()));
        System.out.println("Application-------------");
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }
}
