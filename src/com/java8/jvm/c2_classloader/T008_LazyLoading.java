package com.java8.jvm.c2_classloader;
/**
 * lazyloading
 *     懒加载,jvm规范并没有规定何时加载,jvm虚拟机实现都是用的懒加载, 就是什么时候用才去加载
 *
 * jvm严格规定了什么时候初始化,有五中情况
 *      1. new  getstatic putstatic invokestatic指令, 访问final遍历除外
 *      2. java.lang.reflect对类进行反射调用时
 *      3. 初始化子类的时候, 父类首先初始化
 *      4. 虚拟机启动时, 被执行的主类必须初始化
 *      5. 动态语言支持 java.lang.invoke.MethodHandle解析的结果为 REF_getstatic
 *          REF_putstatic REF_invokestatic的方法句柄时, 该类必须初始化
 */
public class T008_LazyLoading {

    //严格讲应该叫lazy initialzing, 因为java虚拟机规范并没有严格规定什么时候必须loading, 但严格规定了什么时候initializing
    public static void main(String[] args) throws ClassNotFoundException {
        //没有 new 没有访问不会被加载
        //P p;

        //new 了会被加载
        //X x = new  X();

        //打印final 值不需要加载整个类
        //System.out.println(P.i);

        //非 final 会加载
        //System.out.println(P.j);

        //会被加载
        Class.forName("com.java8.jvm.c2_classloader.T008_LazyLoading$P");
    }

    //需要这个类的时候才被加载, 我们怎么证明这个类被加载过
    public static class P {
        final static int i = 8;
        static int j = 9;

        static {
            /**
             * 只要被加载过这个P, 一定是被打印出来的,因为一个classload内存之后它有这几个步骤
             *  1 Loading
             *  2 Linking
             *  3 Initlalizing  这个过程会执行静态语句快, 所以被load进来这个语句快一定被执行过, P一旦打印就证明这个class被load进来了
             *
             */
            System.out.println("P");
        }
    }

    public static class X extends P {
        static {
            System.out.println("X");
        }
    }
}
