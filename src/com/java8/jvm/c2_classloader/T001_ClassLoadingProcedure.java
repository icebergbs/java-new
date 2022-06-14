package com.java8.jvm.c2_classloader;

public class T001_ClassLoadingProcedure {

    public static void main(String[] args) {

        //count ������3��  ������T
        //�Ȱ�T classload �ڴ�
        //����VerificationУ�飬
        //Ȼ�����Preparation��Ĭ��ֵ��ʱ��: T=0��ֵ��
        //��������Resolution,
        //�ڽ���Initializing ��ʼ��������ʼֵ�����ʱ�� count=2��Ȼ������new T T() count++ ���3

        //T���� ��2    ������T�Ȱ�T classload�ڴ����VerificationУ�飬
        //Ȼ�����Preparation ��Ĭ��ֵ���ʱ��T��0��ֵ��count��0 ��������Resolution,  
        //�ڽ���Initializing��ʼ��������ʼֵ���ȵ���new T T() count++ 0���1�� Ȼ��ֵΪ2�� �����Ϊ2

        System.out.println(T.count);
    }


}

class T {

    public static int count = 2;
    public static T t = new T();
    //public static int count = 2;

    private int m = 8;

    private T() {
        count++;
        System.out.println("--" + count);
    }
}