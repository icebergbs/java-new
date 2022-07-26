package com.java8.jvm.c2_classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class T007_MSBClassLoaderWithEncription extends ClassLoader {

    //异或种子值
    public static int seed = 0B10110110;

    //��дfindClass����,Ȼ���ҵ�Ҫload�����Ķ���������. load��֮����ת���ɶ���
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("G:/jee-ecl/Java8/bin/", name.replace(".", "/").concat(".msbclass"));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            //自己想要看就在根据种子异或自己,就相当于解密了
            while ((b = fis.read()) != 0) {
                baos.write(b ^ seed);
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

        return super.findClass(name);
    }

    /**
     * 一般class 就是一个二进制流, 然后自己编译好了,自己手动加密, 怎么加密, 我采用了一个本方法就是异或,异或一下加密在异或一下解密
     * @param args
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        encFile("com.jvm.Hello");

        ClassLoader l = new T007_MSBClassLoaderWithEncription();
        Class clazz = l.loadClass("com.java8.jvm.c2_classloader.Hello");
        Class clazz1 = l.loadClass("com.java8.jvm.c2_classloader.Hello");
        System.out.println(clazz == clazz1);

        Hello hello = (Hello) clazz.newInstance();
        hello.m();

        //��AppClassLoader
        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
        System.out.println(getSystemClassLoader());

    }

    private static void encFile(String name) throws IOException {
        File file = new File("G:/jee-ecl/Java8/bin/", name.replace(".", "/").concat(".class"));
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(new File("G:/jee-ecl/Java8/bin/", name.replaceAll("\\.", "/").concat(".msbclass")));

        int b = 0;
        //把文件所有内容读出来
        while ((b = fis.read()) != -1) {
            //然后进行异或, 异或完在写回去
            fos.write(b ^ seed);
        }

        fis.close();
        fos.close();
    }
}
