package com.java8.javassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 15:57
 */
public class GSWJClassLoader2 {

    private static final Logger LOG = LoggerFactory.getLogger(GSWJClassLoader2.class);


    public static void main(String[] args) throws Throwable {
//        ClassPool cp = ClassPool.getDefault();
//        cp.insertClassPath("D:\\project\\demo\\java8\\src\\main\\java\\com\\java8\\javassist\\java");
//        CtClass cc = cp.get("Hello");
//        CtMethod m = cc.getDeclaredMethod("say");   //xx: 无法写入
//        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
//        Class c = cc.toClass();
//        Hello h = (Hello)c.newInstance();
//        h.say();

        //3. 使用javassist.Loader
        //Translator t = new MyTranslator();
//        ClassPool pool = ClassPool.getDefault();
//        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
//        pool.makeClass("com.java8.javassist.java.Rectangle");
//        pool.makeClass("com.java8.javassist.java.Point");
//        Loader c1 = new Loader(pool);
//
//       // c1.addTranslator(pool, t);
//
//        CtClass point = pool.get("com.java8.javassist.java.Rectangle");
//        CtConstructor m = CtNewConstructor.make(
//                "Point() {}", point);
//        point.addConstructor(m);
//        CtClass ct = pool.get("com.java8.javassist.java.Rectangle");
////        CtConstructor m1 = CtNewConstructor.make(
////                "public Rectangle() {}", ct);
////        ct.addConstructor(m1);
//        ct.setSuperclass(pool.get("com.java8.javassist.java.Point"));
//        Class c = c1.loadClass("com.java8.javassist.java.Rectangle");
//        Object rect = c.newInstance();
//

        Translator t = new MyTranslator();
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("D:\\project\\demo\\java8\\src\\main\\java\\com\\java8\\javassist\\java");
        Loader c1 = new Loader();
        c1.addTranslator(pool, t);
        c1.run("MyApp", args);
    }
}
