package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *       给定一个单链表的头部节点head, 链表长度N,
 *       如果N 为偶数， 前N/2个节点算作左办区，后N/2算作右半区；
 *       如果N 为奇数， 前N/2个节点算作左办区，后N/2+1算作右半区；
 *       左半区从左到右依次记为 L1->l2..., 右半区从左到右依次记为 R1->R2...,
 *       请将单链表调整成 L1->R1->L2->R2...的形式
 *
 *       例如：
 *         1->null, 调整为 1->null
 *         1->2->null, 调整为 1->2->null
 *         1->2->3->null, 调整为 1->2->3->null
 *         1->2->3->4->null, 调整为 1->3->2->4->null
 *         1->2->3->4->5->null, 调整为 1->3->2->4->5->null
 *         1->2->3->4->5->6->null, 调整为 1->4->2->5->3->6->null
 */
public class C20_1x_按照左右半区的方式重新组合单链表 {

    class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }
 


}
