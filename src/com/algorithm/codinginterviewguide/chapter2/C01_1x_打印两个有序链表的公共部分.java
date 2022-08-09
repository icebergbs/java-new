package com.algorithm.codinginterviewguide.chapter2;


/**
 * 题目:
 *      给定两个有序链表的头指针 head1 和 head2, 打印两个链表的公共部分
 *
 */
public class C01_1x_打印两个有序链表的公共部分 {


    /**
     * 解法:
     *  因为是有序链表, 所以从两个链表的头开始进行如下判断:
     *      1. 如果head1 的值小于 head2, 则 head1往下移动
     *      2. 如果head2 的值小于 head1, 则 head2往下移动
     *      3. 如果head1 的值与 head2的值相等, 则打印这个值, 然后 head1 和 head2都往下移动
     *      4. head1 或 head2 有任何一个为 null, 则整个过程停止
     */
    public class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public void printCommonPart(Node head1, Node head2) {
        System.out.println("Common Part: ");
        while (head1 != null  && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {

    }


}
