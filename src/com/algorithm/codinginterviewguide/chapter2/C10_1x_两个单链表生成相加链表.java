package com.algorithm.codinginterviewguide.chapter2;

import java.util.HashMap;

/**
 * 题目:
 *     假如链表中每一个节点的值都在0~9 之间, 那么链表整体就可以代表一个整数.
 *     例如: 9->3->7, 可以代表整数937
 *     给定两个这种链表的头节点head1 和 head2, 请生成代表两个整数相加值的结果链表.
 *     例如: 链表1 为 9->3->7, 链表2 为 6->3, 最后生成新的结果链表为 1->0->0->0
 *
 */
public class C10_1x_两个单链表生成相加链表 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

}
