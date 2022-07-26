package com.java8.jvm.c2_classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class T0012_ClassReloading2 {

    //定义自己新的classloader, 从classloader继承
    private static class MyLocader extends ClassLoader {

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            //首先去找你要load class文件, 如果没有找到让我父亲去load, 如果找到了自己就load, 我们把是不是已经加载过了这段逻辑去掉了,
            // 如果要加载同一个class是覆盖不了的, 但是我直接把 classloader整体干掉就行了
            File file = new File("G:/jee-ecl/Java8/bin/", name.replace(".", "/").concat(".class"));

            if (!file.exists()) return super.loadClass(name);

            try {
                InputStream fis = new FileInputStream(file);
                byte[] b = new byte[fis.available()];
                fis.read(b);

                return defineClass(name, b, 0, b.length);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return super.findClass(name);
        }

    }

    //所以tomcat这么干的, 直接webapplication的整个classloader全部干掉, 重新在加载一遍
    public static void main(String[] args) throws ClassNotFoundException {
        MyLocader m = new MyLocader();
        Class clazz = m.loadClass("com.java8.jvm.c2_classloader.Hello");

        m = new MyLocader();
        Class clazzNew = m.loadClass("com.java8.jvm.c2_classloader.Hello");

        System.out.println(clazz == clazzNew);
    }

}
