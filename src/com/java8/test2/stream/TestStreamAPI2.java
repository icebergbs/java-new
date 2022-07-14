package com.java8.test2.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import com.java8.test.lambda.lambda.Employee;


/*
 * һ��Stream ��������������
 * 	   1. ����Stream
 * 	   2. �м����
 * 	   3. ��ֹ�������ն˲�����
 * */
public class TestStreamAPI2 {
    //�м����
    /* ɸѡ����Ƭ
     * filter--����Lambda���������ų�ĳЩԪ��
     * limit -- �ض�����ʹ��Ԫ�ز���������������
     * skip(n)--����Ԫ�أ�����һ���ӵ���ǰn��Ԫ�ص�����������Ԫ�ز���n�����򷵻�һ����������limit(n)����
     * distinct--ɸѡ��ͨ����������Ԫ�ص�hashCode() ��equals()ȥ���ظ�Ԫ��
     * */

    List<Employee> employee = Arrays.asList(
            new Employee("֣��", 118, 99.99),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 312, 34.5),

            new Employee("����", 39, 34.5));


    @Test
    public void test4() {
        Stream<Employee> s = employee.stream()
                .filter((e) -> e.getAge() > 55)
                .distinct();
        s.forEach(System.out::println);
    }

    @Test
    public void test3() {
        employee.stream()
                .filter((e) -> {
                    System.out.println("��·��");    // &&  || ��·��������� Ч��
                    return e.getAge() > 35;
                })
                .skip(1)
                .limit(2).forEach(System.out::println);
    }


    @Test
    public void test2() {
        employee.stream()
                .filter((e) -> {
                    System.out.println("��·��");    // &&  || ��·��������� Ч��
                    return e.getAge() > 55;
                })
                .limit(2).forEach(System.out::println);
    }


    //�ڲ����������������� Stream API ���     ���ⲿ������Iterator��
    @Test
    public void test1() {
        Stream<Employee> s = employee.stream()
                .filter((e) -> e.getAge() > 55);
        s.forEach(System.out::println);
    }

    /*
     * ӳ��
     * 	map--����Lambda����Ԫ�� ת����������ʽ����ȡ��Ϣ������һ��������Ϊ�������ú����ᱻӦ�õ�ÿ��Ԫ���ϣ�������ӳ���һ���µ�Ԫ�ء�
     *  flatMap--����һ��������Ϊ�����������е�ÿ��ֵ��������һ������Ȼ������������ӳ�һ����
     * */
    @Test
    public void test5() {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
        System.out.println("----------------");

        employee.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
        System.out.println("----------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(TestStreamAPI2::filterCharacter); //{{a,a},{b,b}}
        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });
        System.out.println("----------------");

        Stream<Character> sm = list.stream()
                .flatMap(TestStreamAPI2::filterCharacter); //{a,a,b,b}
        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /*
     * ����
     * 		sorted() -- ��Ȼ����(Comparable)
     * 		sorted(Comparator com) --��������(Comparator)
     */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("dd", "aa", "ee", "bb", "cc");
        list.stream()
                .sorted()
                .forEach(System.out::println);


    }
}
