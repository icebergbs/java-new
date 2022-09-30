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
    public boolean isHuiWenStructxx(Node head) {
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
     * 解法一：
     * @param head
     * @return
     */
/*
  利用栈结构。从左到右遍历链表，遍历的过程中把每个节点依次压入栈中。因为栈是先进后出的，
所以子啊遍历完成后，从栈顶到栈底的节点值出现顺序与原链表从左到右的值出现顺序反过来。那么
，如果一个链表是回文结构，逆序之后，值出现的次序还是一样的，如果不是回文结构，顺序就肯定
对不上。
  额外的栈结构需要O(N)的空间。
 */
    public boolean isPalindromel(Node head) {
         Stack<Node> stack = new Stack<>();
         Node cur = head;
         while (cur != null) {
             stack.push(cur);
             cur = cur.next;
         }
         while (head != null) {
             if (head.value != stack.pop().value) {
                 return false;
             }
             head = head.next;
         }
         return true;
    }

    /**
     * 进阶解法
     * @param head
     * @return
     */
/*
  对方法一进行了优化，虽然也是利用栈结构，但是其实并不需要将所有的节点都压入栈中，
只用压入一半的节点即可。首先假设链表的长度为N, 如果N是偶数，前N/2的节点叫做左半
区，后N/2的节点叫做右半区。如果N是奇数，忽略处于最中间的节点，还是前N/2的节点叫
做左半区，后N/2的节点叫作右半区。
  链表1->2->2->1,左办区： 1,2; 右半区为：2,1
  链表1->2->3->2->1,左办区： 1,2; 右半区为：2,1
  方法二就是把整个链表的右半部分压入栈中，压入完成后，在检查栈顶到栈底值出现的顺
序是否和链表左半部分的值相对应。
 */
    public boolean isHuiWenStruct2(Node head) {

        return false;
    }

}
