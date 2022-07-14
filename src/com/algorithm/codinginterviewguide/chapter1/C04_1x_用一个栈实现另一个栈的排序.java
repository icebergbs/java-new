package com.algorithm.codinginterviewguide.chapter1;

import java.util.Stack;

/**
 * 题目:
     一个栈中元素的类型为整数,现在想将该栈从顶到底按从大到小的顺序排序,
     只许申请一个栈. 除此之外,可以申请新的变量,但不能申请额外的数据结构.如何完成排序?
 *
 */
public class C04_1x_用一个栈实现另一个栈的排序 {

    /**
     * 解答:
     *      1. 如果cur 小于或等于 help的栈顶元素, 则将cur直接压入help;
     *      2. 如果cur 大于 help的栈顶元素, 则将 help的元素逐一弹出, 逐一压入stack,
     *          直到cur 小于或等于help的栈顶元素, 再将 cur压入help.
     *
     *      一直执行以上操作,直到stack中的全部元素都压入到 help.
     * @param stack
     */

    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

}
