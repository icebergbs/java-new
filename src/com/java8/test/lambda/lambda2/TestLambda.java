package com.java8.test.lambda.lambda2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import com.java8.test.lambda.lambda.Employee;
import org.junit.Test;

/*һ.Lambda ���ʽ�Ļ����﷨��
  * 	Java8 ��������һ���µĲ����� ��->" �ò�������Ϊ��ͷ��������Lambda������ 
  * 		��ͷ��������Lambda ���ʽ��ֳ������֣�
  * 	��ࣺLambda ���ʽ�Ĳ����б�
  * 	�Ҳ� ��Lambda ���ʽ������ִ�еĹ��ܣ���Lambda ��
  *   
  *   �﷨��ʽһ���޲������޷���ֵ
  *   				����-> System.out.println("hello");;
  *   �﷨��ʽ�����в������޷���ֵ
  *    			        ��x��-> System.out.println(x);
  *   �﷨��ʽ������ֻ��һ��������С���ſ���ʡ�Բ�д
  *   				  x -> System.out.println(x);
  *   �﷨��ʽ�ģ����������ϲ������з���ֵ������Lambda �����ж������
  *   				  Comparator<Integer> com = (x,y) ->{
						 System.out.println("����ʽ�ӿ�");
						 return Integer.compare(x, y);
					  };
  *   �﷨��ʽ�壺��Lambda ����ֻ��һ����䣬return �ʹ����Ŷ�����ʡ�Բ�д
  *   �﷨��ʽ���� Lambda ���ʽ�Ĳ����б����Ϳ��Բ�д��
  *   				��ΪJVM������ͨ���������ƶϳ�	�������ͣ����������ƶϡ�
  *
  *���� Lambda ���ʽ��Ҫ������ʽ�ӿڡ���֧��
  *		����ʽ�ӿڣ��ӿ���ֻ��һ�����󷽷��Ľӿڣ���Ϊ����ʽ�ӿڡ�
  *					����ʹ��ע�� @FunctionalInterface ���ο��Լ���Ƿ��Ǻ���ʽ�ӿ�
  * */
public class TestLambda {

    @Test
    public void test1() {
        int num = 0;  //jdk1.7 ������final

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hellow Lambda  " + num);

            }

        };
        r.run();

        System.out.println("-----");

        Runnable r1 = () -> System.out.println("Hellow Lambda");
        r1.run();
    }

    @Test
    public void test2() {
        Consumer<String> con = x -> System.out.println(x);
        con.accept("hsfhejjj");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("����ʽ�ӿ�");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

    }

    @Test
    public void test5() {
        //�����ƶ�
        String[] strs = {"aa", "bb", "ccc"};
        List<String> list = new ArrayList<>();

    }

    //���󣺶�һ������������
    @Test
    public void test6() {
        Integer num = operation(100, (x) -> x * x);
        System.out.println("num:" + num);
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }

    List<Employee> employee = Arrays.asList(
            new Employee("֣��", 118, 99.99),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 39, 34.5));

    @Test
    public void test11() {
        Collections.sort(employee, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : employee) {
            System.out.println(emp);
        }
        String upper = strHandler("abfehh", (e) -> e.toUpperCase());
        System.out.println(upper);

        String upper1 = strHandler("serrerertff", (st) -> st.substring(2, 5));
        System.out.println(upper1);

    }

    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }
}
