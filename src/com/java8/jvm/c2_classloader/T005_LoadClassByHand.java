package com.java8.jvm.c2_classloader;

/**
 * 如何把一个类加载到内存?
 *
 * LoadClassByHand利用现有的这个加载器加载一个类的时候怎么加载调用那个类方法呢?
 */
public class T005_LoadClassByHand {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 首先加载拿到app类加载器, 之后调用它的方法 loadclass(), 接下来我把需要加载进来的这个类的名字告诉它, 它就会帮我加载进来.
         */


        /**
         *  加载一个类只需要调用 classLoad的loadclass() 就能够把这个类加载到内存中
         *  加载到内存它会返回一个class类的对象.
         *  加载过程:  在硬盘上找到这个类的 源码,这些源码可能在那些目录下面,把它laad内存,与此同时生成一个class类的对象,把这个对象返回给你.
         */
        Class clazz = T005_LoadClassByHand.class.getClassLoader()
                        .loadClass("com.java8.jvm.c2_classloader.T003_ClassLocderScope");
        System.out.println(clazz.getName());

        //利用类加载器加载资源, 参考坦克图片的加载
        //T005_LoadClassByHand.class.getClassLoader().getResourceAsstream("");
    }
}
