package com.algorithm.codinginterviewguide.chapter2;

import java.util.Stack;

/**
 * 题目:
 *   给定一个链表的头节点head 和一个整数num, 请实现函数将值为num的节点全部删除。
 *
 *   例如，链表为 1->2->3->4->null, num=3, 链表调整后为： 1-》2-》4->null
 *
 */
public class C14_1x_在单链表中删除指定值的节点 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

/*
    方法一： 利用栈或者其他收集节点地方，时间复杂度为O(N), 额外空间复杂度为O(N),
    将值不等于num的节点用栈收集起来，最后将栈底的节点作为新的头节点返回。
 */
    public Node removeValue1(Node head, int num) {
        Stack<Node> stack = new Stack<Node>();
        while (head != null) {
            if (head.value != num) {
                stack.push(head);
            }
            head = head.next;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        return head;
    }


/*
    方法二： 直接调整，时间复杂度为O(N), 额外空间复杂度为O(1)
 */

    public Node removeValue2(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }


    public void deleteNumNodexxxx(Node head, int num) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            if (cur.value == num) {
                if (pre == null) {
                    head = cur.next;
                } else {
                    pre.next = cur.next;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
    }


}
