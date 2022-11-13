package com.design.oop.polymorphism.case1;

/**
 * 多态 一个具体的例子
 * @author bingshan
 * @date 2022/11/12 11:52
 */
public class DynamicArray {

    private static final int DEFAULT_CAPACITY = 10;
    protected int size = 0;
    protected int capacity = DEFAULT_CAPACITY;
    protected Integer[] elements = new Integer[DEFAULT_CAPACITY];

    public int size() { return this.size; }
    public Integer get(int index) { return elements[index];}
    //...省略n多方法...

    public void add(Integer e) {
        ensureCapacity();
        elements[size++] = e;
    }

    protected void ensureCapacity() {
        //...如果数组满了就扩容...代码省略...
    }
}