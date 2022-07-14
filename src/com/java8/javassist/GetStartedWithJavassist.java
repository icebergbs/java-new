package com.java8.javassist;

import javassist.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/9 20:02
 */
public class GetStartedWithJavassist {
    private static final Logger LOG = LoggerFactory.getLogger(GetStartedWithJavassist.class);

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        /**
         * bytecode 读写
         */
        ClassPool pool = ClassPool.getDefault();
        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
        pool.makeClass("com.java8.javassist.java.Rectangle");

        pool.makeClass("com.java8.javassist.java.Point");
        CtClass point = pool.get("com.java8.javassist.java.Point");
        CtConstructor m = CtNewConstructor.make(
                                            "Point() {}", point);
        point.addConstructor(m);
        //直接加载CtClass
        Class pointjava = point.toClass();
        LOG.info("Rectangle class: " + pointjava );

        CtClass rectangle = pool.get("com.java8.javassist.java.Rectangle");
        rectangle.setSuperclass(point);
        //直接获取修改的字节码
        byte[] b = rectangle.toBytecode();
        LOG.info("Rectangle字节码: "+ b.toString());

        //冻结类
        //如果CtClass对象被writeFile(),toClass()或者toBytecode()转换成了类对象，Javassist将会冻结此CtClass对象。
        // 任何对此对象的后续更改都是不允许的。之所以这样做，主要是因为此类已经被JVM加载，
        // 由于JVM本身不支持类的重复加载操作，所以不允许更改。
        rectangle.writeFile("./src/main/java/");
        //代码进行解冻
        rectangle.defrost();
        //如果ClassPool.doPruning被设置为true，那么Javassist将会把已冻结的CtClass对象中的数据结构进行精简，
        // 此举主要是为了防止过多的内存消耗。而精简掉的部分，都是一些不必要的属性(attriute_info结构)。
        // 因此，当一个CtClass对象被精简之后，方法是无法被访问和调用的，但是方法名称，签名，注解可以被访问。
        // 被精简过的CtClass对象不能被再次解冻。
        // 需要注意的是，ClassPool.doPruning的默认值为false。
        //
        //要禁止修剪特定的CtClass, stopPruning()必须提前在该对象上调用
        pool.makeClass("com.java8.javassist.java.Prune");
        CtClass prune = pool.get("com.java8.javassist.java.Prune");
        prune.stopPruning(true);

        //类搜索路径
        pool.insertClassPath("D:\\project\\demo\\java8\\src\\main\\java\\com\\java8\\javassist");
        CtClass hello = pool.get("Hello");
        InputStream is = new FileInputStream(
                "D:\\project\\demo\\java8\\src\\main\\java\\com\\java8\\javassist\\Student.class");
        pool.makeClass(is);
        CtClass student = pool.get("Student");


    }

    GetStartedWithJavassist() {}


}
