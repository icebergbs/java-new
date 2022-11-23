package com.algorithm.codinginterviewguide.chapter2;

import java.util.HashSet;

/**
 * 题目:
 *   给定一个无序单链表的头节点head, 删除其中值重复出现的节点。
 *
 *   例如： 1->2->3->3->4->4->2->1->1->null, 删除值重复的节点之后为
 *         1->2->3->4->null
 *
 *   请按以下要求实现两种方法。
 *   方法1： 如果链表长度为N, 时间复杂度达到O(N)
 *   方法2： 额外空间复杂度为O(1)
 *
 *
 */
public class C13_1x_删除无序单链表中值重复出现的节点 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

/*
    方法一： 利用哈希表，事件复杂度为O(N), 额外空间复杂度为O(N)

 */
    public void removeRep1(Node head) {
        if (head == null) {
            return;
        }
        HashSet<Integer> set = new HashSet<>();
        Node pre = head;
        Node cur = head.next;
        set.add(head.value);
        while (cur != null) {
            if (set.contains(cur.value)) {
                pre.next = cur.next;
            } else {
                set.add(cur.value);
                pre = cur;

            }
            cur = cur.next;
        }
    }

/*
    方法二： 类似选择排序， 时间复杂度为O(N*N)，空间复杂度为O(1).
    例如链表： 1->2->3->4->4->2->2->1->null
    首先是头节点，节点为1，往后检查所有节点值为1的节点，全部删除，链表变为：  1->2->3->4->4->2->2->null
    。。。。。。
 */
    public void removeRep2(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.value == next.value) {
                    pre.next = next.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }
    }

    public void removeRep2xx(Node head) {
        if (head == null) {
            return;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            Integer value = pre.value;
            Node pre2 = pre;
            Node cur2 = cur;
            while (cur2 != null) {
                if (cur2.value == value) {
                    pre2.next = cur2.next;
                } else {
                    pre2 = cur2;
                }
                cur2 = cur2.next;
            }
            pre = cur;
            cur = cur.next;
        }
    }


}
