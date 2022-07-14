package com.algorithm.codinginterviewguide.chapter1;

import java.util.Stack;

/**
 * 题目: 实现一个特殊的栈, 在实现栈的基础功能的基础上, 再实现返回栈中最小元素的操作.
 *
 * 要求:
 *      1. pop. push. getMin 操作的时间复杂度都是 O(1)
 *      2. 设计的栈类型可以使用现成的栈结构
 */
public class C01_1x_设计一个有getMin功能的栈 {

    /**
     * 第一种设计方案:
     *   stackMin保存每一步的最小值
     */
    class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack1() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum <= this.getmin()) {
                this.stackMin.push(newNum);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            int value = this.stackData.pop();
            if (value == this.getmin()) {
                this.stackMin.pop();
            }
            return value;
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }

    }

    /**
     * 第二种设计方案
     */
    class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            this.stackData = new Stack<>();
            this.stackMin = new Stack<>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }

    }

    /**
     * 共同点:
     *      所有操作的时间复杂度都为 O(1), 空间复杂度都为O(n)
     * 区别:
     *      方案一 stackMin 压入时稍省空间, 但是弹出操作稍费时间
     *      方案二 stackMin 压入时稍费空间, 但是弹出操作稍省空间
     */

}
