package com.java8.jvm.c2_classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义类加载器
 */
//第一点继承ClassLoader
public class T006_MSBClassLoader extends ClassLoader {

    //然后重写findClass方法, 然后找到要load进来的二进制内容, load完之后在转换成对象
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("/home/project/javaee/java-new/src", name.replace(".", "/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != 0) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
            baos.close();
            fis.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return super.findClass(name); //throws ClassNotFoundException
    }

    //懒加载就是里面有了就不再去找了,直接用
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader l = new T006_MSBClassLoader();
        Class clazz = l.loadClass("com.java8.jvm.c2_classloader.Hello");
        Class clazz1 = l.loadClass("com.java8.jvm.c2_classloader.Hello");
        System.out.println(clazz == clazz1);

        Hello hello = (Hello) clazz.newInstance();
        hello.m();

        //是AppClassLoader
        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
        System.out.println(getSystemClassLoader());

    }
}
