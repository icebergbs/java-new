package com.java8.test2.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.Test;

import com.java8.test.lambda.lambda.Employee;


public class TestStreamAPILX {

    /*
     * 1  ����{1,2,3,4,5} ����{1,2,9,16,25}
     * */
    @Test
    public void test1() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        IntStream is = Arrays.stream(arr);
        is.map((e) -> e * e)
                .forEach(System.out::println);
    }

    /*
     *  2 ������map��reduce������һ�������ж��ٸ�Employee
     * */
    List<Employee> employee = Arrays.asList(
            new Employee("֣��", 118, 99.99),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 312, 34.5),
            new Employee("С��", 32, 342),
            new Employee("����", 39, 34.5));

    @Test
    public void test2() {
        Optional<Integer> count = employee.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(count);
    }
}
