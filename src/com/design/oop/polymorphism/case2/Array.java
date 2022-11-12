package com.design.oop.polymorphism.case2;

/**
 * @author bingshan
 * @date 2022/11/12 12:22
 */
public class Array implements Iterator {
    private String[] data;

    public boolean hasNext() { //...
        return true;
         }
    public String next() { //...
        return null; }
    public String remove() { //...
            return null; }
    //...省略其他方法...
}
