package com.java8.test2.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.java8.test.lambda.lambda.Employee;


/*
 * ��ֹ����
 *
 * ������ƥ��
 * 	allMath --����Ƿ�ƥ������Ԫ��
 * 	anyMath --����Ƿ�����ƥ��һ��Ԫ��
 * 	noneMath -- ����Ƿ�û��ƥ������Ԫ��
 * 	findFirst -- ���ص�һ��Ԫ��
 * 	findAny -- ���ص�ǰ���е�����Ԫ��
 * 	count -- ��������Ԫ�ص��ܸ���
 * 	max -- �����������ֵ
 * 	min -- ����������Сֵ
 * */
public class TestStreamEND {
    List<Employee> employee = Arrays.asList(
            new Employee("֣��", 118, 99.99),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 312, 34.5),

            new Employee("����", 39, 34.5));

    @Test
    public void test() {
        Optional<Employee> op = employee.stream()
                .sorted((e1, e2) -> -Double.compare(e1.getSalary(), e2.getSalary()))
                .findFirst();
        System.out.println(op.get());
    }

    /*
     * ��Լ
     * 	reduce(T identity,BinaryOperator)
     * 	reduce(BinaryOperator)
     * 	���Խ�����Ԫ�ط�������������õ�һ��ֵ��
     * */
    @Test
    public void tes3() {
        List<Integer> list = Arrays.asList(1, 3, 5, 6, 7);
        Integer sum = list.stream()
                .reduce(1, (x, y) -> x + y);
        System.out.println(sum);

        Optional<Double> op = employee.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /*
     * �ռ�
     * 	 collect -- ����ת��Ϊ������ʽ������һ��Collector�ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
     * */
    @Test
    public void test4() {
        List<String> list = employee.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("�ŵ�����ļ�����-----");
        HashSet<String> hs = employee.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test5() {
        //����
        Long count = employee.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        //ƽ��ֵ
        Double avg = employee.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg);

        //�ܺ�    (���ֵ   ����Сֵ   ��
        Double sum = employee.stream()
                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        //����
        Map<String, List<Employee>> map = employee.stream()
                .collect(Collectors.groupingBy(Employee::getName));
        System.out.println(map);

        //����
        Map<Boolean, List<Employee>> map1 = employee.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() > 50));
        System.out.println(map1);

        DoubleSummaryStatistics dss = employee.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(dss.getMin());

        String str = employee.stream()
                .map(Employee::getName)
                .collect(Collectors.joining("-", "==", "++"));
        System.out.println(str);
    }
}








