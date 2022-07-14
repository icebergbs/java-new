package com.algorithm.codinginterviewguide.chapter1;

import java.util.Stack;

/**
 * 题目: 一个栈依次压入 1. 2. 3. 4. 5. 那么从栈顶到栈底分别为 5. 4. 3. 2. 1.
 *      将这个栈转置后, 从栈顶到栈底为 1. 2. 3. 4. 5. 也就是实现栈中元素的逆序,
 *      但是只能用递归函数来实现, 不能用其他数据结构
 *
 */
public class C02_2x_如何仅用递归函数和栈操作逆序一个栈 {

    /**
     * 本题考察查栈的操作和递归函数的设计, 我们需要设计出两个递归函数
     *
     * 递归函数一: 将栈stack 的栈底元素返回并移除
     */
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    /**
     * 递归函数二: 逆序一个栈, 就是题目要求实现的方法, 具体过程就是如下代码中的 resverse
     */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }

}
