package com.java8.javassist;

import javassist.*;

/**
 * 下面监听器将会在类被加载之前，修改其类型为public
 *
 * @author  bingshan
 * @date 2022/3/13 19:01
 */
public class MyTranslator implements Translator {
    @Override
    public void start(ClassPool pool) throws NotFoundException, CannotCompileException {

    }

    @Override
    public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
        CtClass cc = pool.get(classname);
        cc.setModifiers(Modifier.PUBLIC);
    }
}
