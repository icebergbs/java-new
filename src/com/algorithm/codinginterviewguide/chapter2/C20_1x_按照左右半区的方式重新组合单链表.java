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

    public Node adjustListxxx(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        Node rightFirst = findRightFirstNode(head);
        Node left = head;
        Node right = rightFirst;
        Node pre = null;
        Node next = null;
        while (left != rightFirst) {
            if (pre == null) {
                pre = left;
                left = left.next;
                pre.next = right;
                right = right.next;
                pre = right;
                pre.next = null;
            } else {
                pre.next = left;
                pre = left;
                left = left.next;
                pre.next = right;
                pre = right;
                right = right.next;

                pre.next = null;
            }
        }
        if (right != null) {
            pre.next = right;
        }
        return head;
    }

    public Node findRightFirstNode(Node head) {


        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        if (cur.next == null && cur.next.next == null) {
            return pre.next;
        } else {
            return pre.next.next;
        }
    }

/*
    假设链表的长度为N，时间复杂度为O(N), 额外的空间复杂度为O(1)
    1. 如果链表为空或长度为1， 不用调整
    2. 链表长度大于1时，遍历一遍找到左半区的最后一个节点，记为md.
       从长度为2开始，长度每增加2，md就往后移动一个节点。
    3. 找到md后，将左半区与右半区分离成两个链表（mid.next = null),
       分别记为left(head) 和rigth(mid.next)
    4. 将两个链表按照题目要求合并起来
 */

    public void relocate(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);

    }

    public void mergeLR(Node left, Node right) {
        Node next = null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
 


}
