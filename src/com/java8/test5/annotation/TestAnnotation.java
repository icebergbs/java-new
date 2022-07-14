package com.java8.test5.annotation;

import java.lang.reflect.Method;

import org.junit.Test;

/*
	�ظ�ע���� ����ע��
*/

public class TestAnnotation {
    //checker formaker
    //����Ϊ��  �������һ��ע��  @NonNull  ��java8 ��û�����ô�����ע��
    private Object obj = null;

    @Test
    public void test1() throws Exception {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Method m1 = clazz.getMethod("show");
        MyAnnotation[] ma = m1.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : ma) {
            System.out.println(myAnnotation.value());
        }
    }

    @MyAnnotation("word")
    @MyAnnotation("hello")
    public void show(@MyAnnotation("abc") String str) {

    }
}
