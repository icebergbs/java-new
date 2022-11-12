package com.design.oop.polymorphism.case1;

/**
 *  多态特性的实现方式，利用“继承加方法重写”这种实现方式
 * @author bingshan
 * @date 2022/11/12 11:54
 */
public class Example {

    public static void test(DynamicArray dynamicArray) {
        dynamicArray.add(5);
        dynamicArray.add(1);
        dynamicArray.add(3);
        for (int i = 0; i < dynamicArray.size(); ++i) {
            System.out.println(dynamicArray.get(i));
        }
    }

/*
  多态这种特性也需要编程语言提供特殊的语法机制来实现。在上面的例子中，我们用到了三个语法机制来实现多态。

    第一个语法机制是编程语言要支持父类对象可以引用子类对象，也就是可以将 SortedDynamicArray
    传递给 DynamicArray。

    第一个语法机制是编程语言要支持父类对象可以引用子类对象，也就是可以将 SortedDynamicArray
    传递给 DynamicArray。

    第三个语法机制是编程语言要支持子类可以重写（override）父类中的方法，也就是 SortedDyamicArray
    重写了 DynamicArray 中的 add() 方法。


 */
    public static void main(String args[]) {
        DynamicArray dynamicArray = new SortedDynamicArray();
        test(dynamicArray); // 打印结果：1、3、5
    }
}
