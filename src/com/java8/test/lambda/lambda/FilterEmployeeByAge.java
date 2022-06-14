package com.java8.test.lambda.lambda;

public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {

        return t.getAge() >= 35;
    }

}
