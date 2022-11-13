package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *   给定一个单链表的头节点head, 实现一个调整单链表的函数，使得每K个节点之间逆序。
 *   如果最后不够k个节点一组， 则不调整最后几个节点
 *
 *   例如：
 *     链表： 1->2->3->4->5->6->7->8->null, k=3
 *     调整后： 3->2->1->6->5->4->7->8->null, 其中 7， 8不调整，因为不够一组
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

    public Node reverseListGroupK(Node head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        Node left = head;
        Node right = head;
        while (right != null) {
            len++;
            if (len == k) {
                reverseList(left, right);
            }
        }

    }



    /**
     * 反转链表
     *    FUNCTION-******
     * @param head
     * @return
     */
    private void reverseList(Node head, Node end) {
        Node next = null;
        Node pre = null;
        Node cur = head;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return;
    }



}
