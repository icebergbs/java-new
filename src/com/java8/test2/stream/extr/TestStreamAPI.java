package com.java8.test2.stream.extr;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.Test;

import com.java8.test.lambda.lambda.Employee;


public class TestStreamAPI {

    /*
          1.	����һ�������б���η���һ����ÿ������ƽ�����ɵ��б��أ�
        ��������1��2��3��4��5���� Ӧ�÷��ء�1��4��9��16��25����
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    /*
     2.	������ map �� reduce ������һ�������ж��ٸ�Employee�أ�
     */
    List<Employee> emps = Arrays.asList(
//			new Employee(102, "����", 59, 6666.66, Status.BUSY),
//			new Employee(101, "����", 18, 9999.99, Status.FREE),
//			new Employee(103, "����", 28, 3333.33, Status.VOCATION),
//			new Employee(104, "����", 8, 7777.77, Status.BUSY),
//			new Employee(104, "����", 8, 7777.77, Status.FREE),
//			new Employee(104, "����", 8, 7777.77, Status.FREE),
//			new Employee(105, "����", 38, 5555.55, Status.BUSY)
    );

    @Test
    public void test2() {
        Optional<Integer> count = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }
}
