package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *       给定两个有序单链表的头节点head1 和 head2, 请合并两个有序链表，合并后的链表依然有序，
 *       并返回合并后链表的头节点
 */
public class C19_1x_合并两个有序的单链表 {

    class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }


/*
    假设两个链表的长度分别为M和N, 直接给出时间复杂度为O(M+N),额外空间复杂度为O(1)
    1. 如果两个链表中一个为空，无须合并过程，返回另一个链表的头节点即可。
    2. 比较head1 和 head2的值，小的节点也是合并后链表的头节点，记为head;在之后的步骤里，
哪个链表的头节点的值更小，另一个链表的所有节点都会依次插入到这个链表中。
    3. 从头部开始一起遍历两个节点的值，然后根据大小关系做出不同的调整，同时用一个变量pre表示
上次比较时值较小的节点。
    4. 把没有遍历到的有序部分直接拼接到最后。
    5. 返回合并后链表的头节点head。
 */
    public Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }

        Node head = head1.value < head2.value ? head1 : head2;
        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
 


}
