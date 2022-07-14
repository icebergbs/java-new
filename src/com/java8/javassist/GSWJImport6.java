package com.java8.javassist;

import com.java8.javassist.java.Hello1;
import com.java8.javassist.java.Point;
import com.java8.javassist.java.Point1;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.FieldInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 15:57
 */
public class GSWJImport6 {

    private static final Logger LOG = LoggerFactory.getLogger(GSWJImport6.class);


    public static void main(String[] args) throws Throwable {
        ClassPool pool = ClassPool.getDefault();
        pool.importPackage("com.java8.javassist.java");
        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
        //pool.makeClass("com.java8.javassist.java.Point");
        CtClass cc = pool.get("com.java8.javassist.java.Point1");
        CtMethod m = cc.getDeclaredMethod("move");
        //目标方法的参数  $0, $1, $2, ..  $0等价于this关键字
        //insertBefore方法中的代码段是被大括号{}包围的，此方法只接受一个被大括号包围的代码段入参。
        //$$是被逗号分隔的所有参数列表的缩写
        m.insertBefore("{ System.out.println($1); System.out.println($2); }");

        //添加一个方法
        CtMethod ti = CtNewMethod.make(
                "public void testImport(Hello1 h) { h.testImport(); } ", cc);
        cc.addMethod(ti);

        //通过反射调用新生成的方法
        Class  pointClazz = cc.toClass();
        Object pointObj = pointClazz.newInstance();
        Method testImportMethod = pointClazz.getDeclaredMethod("testImport", Hello1.class);

        CtClass ch = pool.get("com.java8.javassist.java.Hello1");
        Class helloClazz = ch.toClass();
        Object helloObj = helloClazz.newInstance();

        testImportMethod.invoke(pointObj, helloObj);

        cc.writeFile("./src/main/java/");
    }
}
