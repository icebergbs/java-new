package com.java8.javassist;/**
 * @author bingshan
 * @date 2021/12/13 20:05
 */

import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

/**
 *新增一个方法
 *
 * @author bingshan
 * @date 2021/12/13 20:05
 */
@Slf4j
public class JavasistTest2 {
    public static void main(String[] args) throws CannotCompileException, IOException {
        //找到本文件路径
        URL url = JavasistTest2.class.getClassLoader().getResource("");
        String file = url.getFile();
        System.out.println("文件存储路径：" + file);
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.java8.javassist.Hello");

        //创建一个类名为"hello"，传递参数的顺序为(int,double)，没有返回值的类
        /*
        CtMethod（...）源代码：
        public CtMethod(CtClass returnType,//这个方法的返回值类型，
                        String mname, //（method name）方法的名字是什么
                        CtClass[] parameters, //方法传入的参数类型是什么
                        CtClass declaring //添加到哪个类中
                        ) {....}
         */
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "hello", new CtClass[]{CtClass.intType, CtClass.doubleType}, ctClass);
        //设置hello 方法的权限
        ctMethod.setModifiers(Modifier.PUBLIC);
        //向ctClass中添加这个方法
        ctClass.addMethod(ctMethod);

        //添加一个变量
        CtField ctField = new CtField(CtClass.intType, "value", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);

        //给变量添加set() get（）
        CtMethod setValue = new CtMethod(CtClass.voidType, "setValue", new CtClass[]{CtClass.intType}, ctClass);
        setValue.setModifiers(Modifier.PUBLIC);
        //设置方法体
        setValue.setBody("this.value = $1;");
        ctClass.addMethod(setValue);

        CtMethod getValue = new CtMethod(CtClass.intType, "getValue", new CtClass[]{}, ctClass);
        getValue.setModifiers(Modifier.PUBLIC);
        //设置方法体
        getValue.setBody("return this.value;");
        ctClass.addMethod(getValue);

        CtMethod ctMethod1 = new CtMethod(CtClass.voidType, "hello1", new CtClass[]{CtClass.intType, CtClass.doubleType}, ctClass);
        //设置hello 方法的权限
        ctMethod1.setModifiers(Modifier.PUBLIC);
        //设置方法体
        ctMethod1.setBody("this.value = $1 + $2;");
        //在方法体的前后分别插入代码
        ctMethod1.insertBefore("System.out.println(\"我在前面插入了：\" + $1);");
        ctMethod1.insertAfter("System.out.println(\"我在后面插入了：\" + $1);");

        //向ctClass中添加这个方法
        ctClass.addMethod(ctMethod1);


        //写入本地
        ctClass.writeFile("./src");
    }


}
