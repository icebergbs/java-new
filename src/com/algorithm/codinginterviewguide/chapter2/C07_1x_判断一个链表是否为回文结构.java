package com.algorithm.codinginterviewguide.chapter2;

import java.util.Stack;

/*
题目:
    给定一共链表的头节点head, 请判断该链表是否为回文结构

    例如：
         1->2->1, 返回true
         1->2->2->1, 返回true
         15->6->15, 返回true
         1->2->3, 返回false

     进阶：
         如果链表长度为N, 时间复杂度到达O(N), 额外空间复杂度达到O(1)

 */
public class C07_1x_判断一个链表是否为回文结构 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 普通解法
     * @param head
     * @return
     */
    public boolean isHuiWenStruct(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        Node cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        Stack<Node> stack = new Stack<>();
        cur = head;
        int i = 0;
        while (cur != null) {
            if (i < len / 2) {
                stack.add(cur);
            }
            if (i == len / 2 && len % 2 == 0) {
                if (stack.peek().getValue() == cur.getValue()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
            if (i > len / 2) {
                if (stack.peek().getValue() == cur.getValue()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 进阶解法
     * @param head
     * @return
     */
    public boolean isHuiWenStruct2(Node head) {

        return false;
    }

}
