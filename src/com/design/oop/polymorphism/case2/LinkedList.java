package com.design.oop.polymorphism.case2;

/**
 * @author bingshan
 * @date 2022/11/12 12:23
 */
public class LinkedList implements Iterator {
    private LinkedList head;

    public boolean hasNext() { return true; }
    public String next() { return null;}
    public String remove() { return null; }
    //...省略其他方法...
}
