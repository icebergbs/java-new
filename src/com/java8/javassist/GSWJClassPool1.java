package com.java8.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 11:36
 */
public class GSWJClassPool1 {

    private static final Logger LOG = LoggerFactory.getLogger(GSWJClassPool1.class);

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
        pool.makeClass("com.java8.javassist.java.Detach");
        CtClass detach = pool.get("com.java8.javassist.java.Detach");
        detach.writeFile("./src/main/java/");
        //1. 避免内存溢出
        //从ClassPool 移除 CtClass对象
        detach.detach();
        //另一个方法， 新的ClassPool对象替换旧的ClassPool对象
        pool = new ClassPool(true);

        //2. 级联ClassPools
        //如果应用运行在JBOSS/Tomcat上, 那么创建多个ClassPool对象将会很有必要。
        // 因为每个类加载其都将会持有一个ClassPool的实例。
        // 应用此时最好不用getDefault()方法来创建ClassPool对象，而是使用构造来创建。
        //多个ClassPool对象像java.lang.ClassLoader一样做级联
        ClassPool parent = ClassPool.getDefault();
        ClassPool child = new ClassPool(parent);
        child.insertClassPath("./classes");
        //如果child.get()被调用，子ClassPool将会首先从父ClassPool进行查找。
        // 当父ClassPool查找不到后，然后将会尝试从./classes目录进行查找。

        //如果child.childFirstLookup = true, 子ClassPool将会首先查找自己的目录
        child.appendSystemPath();
        child.childFirstLookup = true;

        //3. 为新类重命名
        ClassPool pool1 = ClassPool.getDefault();
        pool1.makeClass("Point");
        CtClass cc = pool1.get("Point");
        CtClass cc1 = pool1.get("Point"); //cc1 和 cc一致
        cc.setName("Pair");
        CtClass cc2 = pool1.get("Pair");
        pool1.makeClass("Point");
        CtClass cc3 = pool1.get("Point"); //cc3 和 cc2 不一致

        //4. 从冻结类中创建新类
        //writeFile()方法或者toBytecode()转变成类文件的时候，Javassist将不允许对这个CtClass对象有任何修改。
        cc3.writeFile();
//        cc3.setName("Pair");  //wrong since writeFile() has been called.
        //为避免这种限制可以使用 getAndRename()
        CtClass cc4 = pool1.getAndRename("Point", "Pair1");  // xxx class not find by no cache
    }
}
