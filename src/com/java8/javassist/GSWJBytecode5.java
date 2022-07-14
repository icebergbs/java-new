package com.java8.javassist;

import javassist.bytecode.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/13 15:57
 */
public class GSWJBytecode5 {

    private static final Logger LOG = LoggerFactory.getLogger(GSWJBytecode5.class);


    public static void main(String[] args) throws Throwable {
        //1。 获取ClassFile对象
        ClassFile cf = new ClassFile(false, "TestByteCode", null);
        cf.setInterfaces(new String[] { "java.lang.Cloneable" });
        FieldInfo f = new FieldInfo(cf.getConstPool(), "width", "I");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f);

        //2. 添加和删除成员
        FieldInfo f1 = new FieldInfo(cf.getConstPool(), "add", "Point");
        f.setAccessFlags(AccessFlag.PUBLIC);
        cf.addField(f1);
        List<FieldInfo> fields = cf.getFields();
        LOG.info("Fields Info" + fields.toString());
        fields.remove(1);

//        MethodInfo m = new MethodInfo(cf.getConstPool(),  "addMethod", "I");
//        m.setAccessFlags(AccessFlag.PUBLIC);
//        cf.addMethod(m);

        //3 遍历方法体
//        MethodInfo minfo = cf.getMethod("addMethod");
//        CodeAttribute cAttr =
//                new CodeAttribute(cf.getConstPool(), 1, 1, "return 1;".getBytes(), null);
//        minfo.setCodeAttribute(cAttr);
//        CodeAttribute ca = minfo.getCodeAttribute();
//        CodeIterator i = ca.iterator();
//        //i.begin();
//        while (i.hasNext()) {
//            int index = i.next();
//            int value = i.byteAt(index);
//            LOG.info("index: " + i + ", value: " + Mnemonic.OPCODE[value]);
//        }


        cf.write(new DataOutputStream(new FileOutputStream("./src/main/java/com/java8/javassist/java/TestByteCode.class")));




    }
}
