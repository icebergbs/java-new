package com.java8.jvm.c2_classloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

/**
 * 自定义类加载器
 *  1. 继承ClassLoader
 *  2. 重写模板方法 findClass
 *      调用 defineClass
 *
 *
 *  ClassLoader源码解析
 *  protected Class<?> loadClass(String name, boolean resolve)
 *     {
 *         synchronized (getClassLoadingLock(name)) {
 *             // First, check if the class has already been loaded
 *             Class<?> c = findLoadedClass(name);
 *             if (c == null) {
 *                 try {
 *                     if (parent != null) {
 *                         c = parent.loadClass(name, false);
 *                     } else {
 *                         c = findBootstrapClassOrNull(name);
 *                     }
 *                 } catch (ClassNotFoundException e) {
 *                     // ClassNotFoundException thrown if class not found
 *                     // from the non-null parent class loader
 *                 }
 *
 *                 if (c == null) {
 *                     // If still not found, then invoke findClass in order
 *                     // to find the class.
 *                     long t1 = System.nanoTime();
 *                     c = findClass(name);
 *
 *                     // this is the defining class loader; record the stats
 *                     sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
 *                     sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
 *                     sun.misc.PerfCounter.getFindClasses().increment();
 *                 }
 *             }
 *             if (resolve) {
 *                 resolveClass(c);
 *             }
 *             return c;
 *         }
 *
 *   1. 上来先找,找不到去父类找, 一圈都找不到, 只能回来自己加载
 *   2. findclass 权限是 protected, 只能在子类里访问
 *   3. 所以自定义classloader只用重写 findclass(),  这个模式就是 钩子函数模板方法
 */
//第一点继承ClassLoader
public class T006_MSBClassLoader extends ClassLoader {
    private static final Logger LOG = LoggerFactory.getLogger(T006_MSBClassLoader.class);
    //然后重写findClass方法, 然后找到要load进来的二进制内容, load完之后在转换成对象
    // FileInputStream > InputStream,   ByteArryOutputStream 转换成一个二进制字节数组,再toByteArray(),再用defineClass来变成一个class类对象
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("D:/project/demo/java-new/src/", name.replace(".", "/").concat(".class"));
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fis.read()) != -1) {
                baos.write(b);
            }
            byte[] bytes = baos.toByteArray();
            System.out.println(bytes);
            baos.close();
            fis.close();  //不严谨

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


        Hello hello = (Hello) clazz.newInstance();
        hello.m();

        //是AppClassLoader
        System.out.println(l);
        System.out.println(l.getClass().getClassLoader());
        System.out.println(l.getParent());
        System.out.println(getSystemClassLoader());

    }
}
