package com.java8.proxy.jdk;


import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author bingshan
 * @date 2021/12/21 14:57
 */
public class ProxyUtils {

    /*
     * 将根据类信息 动态生成的二进制字节码保存到硬盘中，
     * 默认的是clazz目录下
     * params :clazz 需要生成动态代理类的类
     * proxyName : 为动态生成的代理类的名称
     */
    public static void generateClassFile(Class clazz, String proxyName) throws FileNotFoundException {
        //根据类信息和提供的代理类名称，生成字节码
        byte[] classFile = ProxyGenerator.generateProxyClass(proxyName, clazz.getInterfaces());
        String paths = clazz.getResource(".").getPath();
        System.out.println(paths);

        try (FileOutputStream out = new FileOutputStream(paths + proxyName + ".class")) {
            //保留到硬盘中    out/production/java8/com/java8/proxy/jdk/
            out.write(classFile);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
