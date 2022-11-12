package com.design.oop.polymorphism.case2;

/**
 *    接口类来实现多态特性
 * @author bingshan
 * @date 2022/11/12 12:23
 */
public class Demo {

    private static void print(Iterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
/*
  在这段代码中，Iterator 是一个接口类，定义了一个可以遍历集合数据的迭代器。
Array 和 LinkedList 都实现了接口类 Iterator。
我们通过传递不同类型的实现类（Array、LinkedList）到 print(Iterator iterator) 函数中，
支持动态的调用不同的 next()、hasNext() 实现。
 */
    public static void main(String[] args) {
        Iterator arrayIterator = new Array();
        print(arrayIterator);

        Iterator linkedListIterator = new LinkedList();
        print(linkedListIterator);
    }
}
