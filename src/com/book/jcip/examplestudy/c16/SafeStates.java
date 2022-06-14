package com.book.jcip.examplestudy.c16;

import com.book.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

//不可变对象的初始化安全性
@ThreadSafe
public class SafeStates {

    //对于含有final域的对象, 当构造函数完成时, 构造函数对final域的所有写入操作,
    // 以及对通过这些域可以到达的任何变量的写入操作, 都将被 " 冻结"
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        states.put("wyoming", "wy");
    }

    public String getAbbreviationn(String s) {
        return states.get(s);
    }
}
