package com.java.bean;

/**
 * @author bingshan
 * @date 2022/5/6 14:16
 */
public class Persion {
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                '}';
    }
}
