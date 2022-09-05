package com.java8.jvm.c2_classloader;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author bingshan
 * @date 2022/8/13 12:27
 */
public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
        pool.makeClass("com.java8.javassist.clazz.Detach");
        CtClass detach = pool.get("com.java8.javassist.clazz.Detach");
        detach.writeFile("./src/");

        T006_MSBClassLoader myClassLoader = new T006_MSBClassLoader();
        Class detach1 = myClassLoader.loadClass("com.java8.javassist.clazz.Detach");
        Object detach11 = detach1.newInstance();
        System.out.println(detach11.getClass().getClassLoader());
        myClassLoader = null;
        detach1 = null;

        System.gc();


        T006_MSBClassLoader myClassLoader2 = new T006_MSBClassLoader();
        Class detach2 = myClassLoader2.loadClass("com.java8.javassist.clazz.Detach");
        Object detach22 = detach2.newInstance();
        System.out.println(detach22.getClass().getClassLoader());

    }

}



//public class MyCustomClassLoader extends ClassLoader {
//    // 加密的
//    classprivate Collection encryptClass = new HashSet<>();
//    // 忽略的类，未加密的类
//    private Collection skipClass = new HashSet<>();public void init() {
//        skipClass.add("lang.classloader.encrypt.EncryptApp");
//        encryptClass.add("lang.classloader.encrypt.MyClass");}
//        @Overridepublic Class>
//    loadClass(String name) throws ClassNotFoundException {
//        // 由父类加载的类
//        if (name.startsWith("java.")     && !encryptClass.contains(name)     && !skipClass.contains(name)) {
//            return super.loadClass(name); }
//        // 未加密的类
//        else if (skipClass.contains(name)) {
//            try {
//                String classPath = name.replace('.', '/') + ".class";
//                //返回读取指定资源的输入流
//                URL resource = getClass().getClassLoader().getResource(classPath);
//                InputStream is = resource != null ? resource.openStream() : null;     if (is == null) {
//                    return super.loadClass(name);     }
//                    byte[] b = new byte[is.available()];     is.read(b);
//                    //将一个byte数组转换为Class类的实例
//                return defineClass(name, b, 0, b.length);
//            } catch (IOException e) {
//                throw new ClassNotFoundException(name, e);
//            } }
//        // 加密的类
//        return findClass(name);}
//        @Overrideprotected Class> findClass(String name) throws ClassNotFoundException {
//        // 加载类文件内容
//        byte[] bytes = getClassFileBytesInDir(name);
//        // 解密
//        byte[] decodedBytes = decodeClassBytes(bytes);
//        // 初始化类，由 jvm 实现
//        return defineClass(name, decodedBytes, 0, bytes.length);}
//        // 读取加密class文件
//    private static byte[] getClassFileBytesInDir(String className) throws ClassNotFoundException {
//        try {   return FileUtils.readFileToByteArray(
//}
//    new File(className.replace(".", "//") + ".class_"));
//    } catch (IOException e) {
//        throw new ClassNotFoundException(className, e); }}}
