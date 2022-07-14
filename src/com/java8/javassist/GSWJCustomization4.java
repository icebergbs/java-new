package com.java8.javassist;

import com.java8.javassist.java.Author;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javassist.CtClass.intType;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 15:57
 */
public class GSWJCustomization4 {

    private static final Logger LOG = LoggerFactory.getLogger(GSWJCustomization4.class);


    public static void main(String[] args) throws Throwable {
        //将CtClass.debugDump设置为目录名称之后，所有被Javassist生成或修改的类文件将会被保存到此目录中
        CtClass.debugDump = "./dump";

        ClassPool pool = ClassPool.getDefault();
        //创建无成员方法   CtNewMethod附带的工厂方法创建，然后利用CtClass.addMethod()将其追加
        //pool.makeClass("com.java8.javassist.java.Point");
        CtClass cc = pool.get("com.java8.javassist.java.Point");
        CtMethod m = cc.getDeclaredMethod("move");
        //目标方法的参数  $0, $1, $2, ..  $0等价于this关键字
        //insertBefore方法中的代码段是被大括号{}包围的，此方法只接受一个被大括号包围的代码段入参。
        //$$是被逗号分隔的所有参数列表的缩写
        m.insertBefore("{ System.out.println($1); System.out.println($2); }");

        //$cflow
        //代表着“流程控制”。这个只读变量会返回方法的递归调用深度
        CtMethod cm = cc.getDeclaredMethod("fact");
        cm.useCflow("fact");
        cm.insertBefore("if ($cflow(fact) == 0)"
                + "    System.out.println(\"fact \" + $1);");

        //addCatch()
        //将代码段插入到方法体中进行执行，在执行过程中一旦方法体抛出exception，可以控制给发送给客户端的返回
        CtClass etype = ClassPool.getDefault().get("java.io.IOException");
        m.addCatch("{System.out.println($e); throw $e; }", etype);

        //2. 修改方法体
        //利用源文本替换现有表达式     xxxx没有生效
        CtMethod m1 = cc.getDeclaredMethod("move1");
        m1.instrument(new ExprEditor() {
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getClassName().equals("Point") && m.getMethodName().equals("move1"))
                        m.replace("{ $1 = 0; $_ = $proceed($$); }");
            }
        });


        //3. 添加新方法或字段
        //添加一个方法
        CtMethod xm = CtNewMethod.make(
                "public int xmove(int dx) { x += dx; return x;}", cc);
        cc.addMethod(xm);
        //如果目标对象和目标方法的名字也传递给了make方法，那么此方法也可以包含$proceed
        CtMethod ym = CtNewMethod.make(
                "public int ymove(int dy) { $proceed(0, dy); return y;}",
                cc, "this", "move");
        cc.addMethod(ym);
        //另一种方式来添加新方法，你可以首先创建一个abstract方法，然后赋予它方法体：
        //如果一个abstract方法被添加到了类中，此时Javassist会将此类也变为abstract，
        //为了解决这个问题，你不得不利用setBody方法将此类变回非abstract状态。
        CtMethod am = new CtMethod(intType, "amove",
                new CtClass[] { intType }, cc);
        cc.addMethod(am);
        am.setBody("{ x += $1; return x; }");
        cc.setModifiers(cc.getModifiers() & ~Modifier.ABSTRACT);
        //相互递归调用方法
        //当一个方法调用另一个为添加到操作类中的方法时，
        // Javassist是无法编译此方法的（Javassist可以编译自己调用自己的递归方法）。
        // 为了添加相互递归调用的方法到类中，你需要如下的窍门来进行。
        // 假设你想添加m和n方法到cc中:
        CtMethod mm = CtNewMethod.make("public abstract int mm(int i);", cc);
        CtMethod nn = CtNewMethod.make("public abstract int nn(int i);", cc);
        cc.addMethod(mm);
        cc.addMethod(nn);
        mm.setBody("{ return ($1 <= 0) ? 1 : (nn($1 - 1) * $1); }");
        nn.setBody("{ return mm($1); }");
        cc.setModifiers(cc.getModifiers() & ~Modifier.ABSTRACT);
        //添加字段
        CtField f = new CtField(intType, "z", cc);
        cc.addField(f);
        //添加字段，设置初始值
        CtField fVal = new CtField(intType, "zVal", cc);
        cc.addField(fVal, "0");
        //成员移除
        //可以调用CtClass类中的removeField或者removeMethod来进行。
        //而移除CtConstructor，可以通过调用removeConstructor方法来进行。

        //4  Annotations
        //CtClass，CtMethod，CtField和CtConstructor
        // 提供了getAnnotations这个快捷的方法来进行注解的读取操作，它会返回注解类型对象
        Object[] all = cc.getAnnotations();
        Author author = (Author) all[0];
        String name = author.name();
        int year = author.year();
        LOG.info("name: " + name + ", year: " + year);

        //5 运行时类支持
        //有些基于Javassist编译器生成的字节码，则需要javassist.runtime这种运行时支持类包的支持（更多细节请访问此包的API）

        //6 导入
        //所有的源码中的类名，必须是完整的（必须包含完整的包名），但是java.lang包例外
        //为了让编译器能够找到类名锁对应的包，可以通过调用ClassPool的importPackage方法来进行
        pool.importPackage("com.java8.javassist.java");
        CtClass test = pool.makeClass("Test");
        CtField tF = CtField.make("public Point p;", test);
        test.addField(tF);
        test.writeFile("./src/main/java/com/java8/javassist/clazz");
        //第二行代表引入java.awt包，那么第三行就不会抛出错误，因为编译器可以将Point类识别为java.awt.Point。
        //需要注意的是，importPckage方法不会影响到ClassPool中的get方法操作，只会影响到编译器的包导入操作。
        // get方法中的参数在任何情况下，必须是完整的，包含包路径的。

        //7 限制

        //7可变参数
        //目前，Javassist无法直接支持可变参数。
        //参数类型int...变成了int[]数组，Modifier.VARARGS被添加到了方法修改器中。
        //为了能够在Javassist编译器中调用此方法，你需要这样来：
        //1:  length(new int[] { 1, 2, 3 });
        CtMethod argsM = CtMethod.make("public int length(int[] args) { return args.length; } ", cc);
        argsM.setModifiers(argsM.getModifiers() | Modifier.VARARGS);
        cc.addMethod(argsM);

        


        cc.writeFile("./src/main/java/");


    }
}
