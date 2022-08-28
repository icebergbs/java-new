package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *    分别实现反转单向链表和反转双向链表的函数
 *
 *
 * 要求：
 *      如果链表长度为N, 时间复杂度要求为O(N), 额外空间复杂度要求为O(1)
 *
 */
public class C04_1x_反转单向和双向链表 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 反转单向链表
     */
    public Node reserveListxx(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = null;
        Node cur = head;
        while (cur != null) {
            Node res = cur;
            cur = cur.next;
            res.next = newHead;
            newHead = res;
        }

        return newHead;
    }

    public Node reserveList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双向链表
     */
    class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;
        public DoubleNode(int data) {
            this.value = data;
        }
    }

    public DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre.last = head;
            next.last = null;
            pre = head;
            head = next;
        }
        return pre;
    }


}
