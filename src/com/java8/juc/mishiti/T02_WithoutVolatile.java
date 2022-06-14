package com.java8.juc.mishiti;

import java.util.ArrayList;
import java.util.List;

public class T02_WithoutVolatile {

    //volatile ���� 
    /**
     * volatile һ��Ҫ������ֵͨ����Ҫ��������ֵ
     * volatile�����������ͣ�������ö���ָ���������һ��new�����Ķ���
     * �����������ĳ�Ա������ֵ�ı��ˣ����޷��۲쵽��
     */
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }


    public static void main(String[] args) {
        /**
         *
         *
         */
        T01_WithoutVolatile c = new T01_WithoutVolatile();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);


            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    break;
                }

            }
            System.out.println("t2����");
        }, "t2").start();

    }

}
