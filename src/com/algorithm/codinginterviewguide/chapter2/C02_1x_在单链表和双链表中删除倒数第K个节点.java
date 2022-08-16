package com.algorithm.codinginterviewguide.chapter2;


/**
 * 题目:
 *      分别实现两个函数, 一个可以删除单链表中倒数第K个节点, 另一个可以删除双链表中倒数第K个节点.
 *
 *   要求:
 *      如果链表长度为N, 时间复杂度达到O(N), 额外空间复杂度达到O(1)
 *
 */
public class C02_1x_在单链表和双链表中删除倒数第K个节点 {

    /**
     * 解法:
     *    就让让链表从头开始走到尾,每移动一步就让K的减一
     *    链表: 1 2 3    k=4
     *       走到节点: 1 2 3
     *       K变化: 3 2 1
     *    链表: 1 2 3    k=3
     *       走到节点: 1 2 3
     *       K变化: 2 1 0
     *    链表: 1 2 3    k=2
     *       走到节点: 1 2 3
     *       K变化: 1 0 -1
     *
     *    有以上三中情况可知:
     *         K > 0  根本没有倒数第K个节点
     *         K = 0  倒数第K个就是头节点, 直接返回 head.next
     *         K < 0
     *              先明确一点, 删除链表的头节点子后的某个节点, 实际上需要找到要删除节点的前一个节点
     *              K<0,找前一个节点的方法:
     *                  1. 重新从头节点开始走, 每移动一步,就让K+1
     *                  2. 当K等于0时, 移动停止, 移动到的节点就是要删除节点的前一个节点
     *                  第一次遍历后, K的值变为K-N, 第二次遍历时, K的值不断+1,加到0就停止遍历,
     *                  第二次遍历会停到第N-K个节点的位置.     K - N + x = 0 ;  x = N-K
     *
     */
    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        Node cur = head;
        while (cur != null) {
            lastKth --;
            cur = cur.next;
        }

        if (lastKth == 0) {
            head = head.next;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;

    }

    /**
     * 双向链表和单项链表的处理方式几乎是一样的, 注意last指针的重联即可
     * @param
     */
    class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    public DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        DoubleNode cur = head;
        while (cur != null) {
            lastKth --;
            cur = cur.next;
        }

        if (lastKth == 0) {
            head = head.next;
            head.last = null;
        }

        if (lastKth < 0) {
            cur = head;
            while (++lastKth != 0) {
                cur = cur.next;
            }
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if (newNext != null) {
                newNext.last = cur;
            }
        }
        return head;

    }




    public static void main(String[] args) {

    }


}
