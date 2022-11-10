package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *   给定一个单链表的头节点head, 实现一个调整单链表的函数，使得每K个节点之间逆序。
 *   如果最后不够k个节点一组， 则不调整最后几个节点
 *
 *
 */
public class C12_2x_将单链表的每K个节点之间逆序 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }



}
