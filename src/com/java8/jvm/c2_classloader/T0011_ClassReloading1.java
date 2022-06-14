package com.java8.jvm.c2_classloader;

public class T0011_ClassReloading1 {

    public static void main(String[] args) throws ClassNotFoundException {
        T006_MSBClassLoader msbClassLoader = new T006_MSBClassLoader();
        Class clazz = msbClassLoader.loadClass("com.java8.jvm.c2_classloader.Hello");

        msbClassLoader = null;
        System.out.println(clazz.hashCode());
        //��һ��classloader ��Ϊ��
        msbClassLoader = null;
        //��newһ��classloader
        msbClassLoader = new T006_MSBClassLoader();
        //��newͬ����һ��class
        Class clazz1 = msbClassLoader.loadClass("com.java8.jvm.c2_classloader.Hello");
        System.out.println(clazz1.hashCode());
        //����class�Ƿ���ȣ���     true :˫��ί�ɡ�������ô���������������ø���ȥ�ң��ҵ��˾Ͳ���ȥ���¼���
        System.out.println(clazz == clazz1);

    }


}
