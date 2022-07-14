package com.java8.javassist;/**
 * @author bingshan
 * @date 2021/12/13 18:59
 */

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;

import java.io.IOException;

/**
 *Javassist是可以动态编辑Java字节码的类库。
 * 它可以在Java程序运行时定义一个新的类，并加载到JVM中；还可以在JVM加载时修改一个类文件。
 * Javassist使用户不必关心字节码相关的规范也是可以编辑类文件的。
 *
 * @author bingshan
 * @date 2021/12/13 18:59
 */
public class JavassistTest {

    public static void main(String[] args) throws CannotCompileException, IOException {
        ClassPool cp = ClassPool.getDefault();
        //定义类
        CtClass stuClass = cp.makeClass("com.java8.javassist.Student");
        stuClass.writeFile("./src");


    }
}
