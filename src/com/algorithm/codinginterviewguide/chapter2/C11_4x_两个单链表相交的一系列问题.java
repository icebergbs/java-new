package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      在本题中,单链表可能有环,也可能无环.给定两个单链表的头节点head1 和 head2,
 *      这两个链表可能相交,也可能不相交.
 *      请实现一个函数, 如果两个链表相交,请返回相交的第一个节点; 如果不相交,返回null
 *
 *  要求:
 *     如果链表1的长度为N, 链表2的长度为M, 时间复杂度请到达O(N+M), 额外空间复杂度请达到O(1)
 *
 *
 */
public class C11_4x_两个单链表相交的一系列问题 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

}
