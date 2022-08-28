package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      给定链表的头节点head, 实现删除链表的中间节点的函数
 *
 *      例如:
 *          不删除任何节点:
 *          1->2, 删除节点1;
 *          1->2->3, 删除节点2;
 *          1->2->3->4, 删除节点2;
 *          1->2->3->4->5, 删除节点3;
 *
 *          进阶:
 *          给定链表的头节点head, 整数a 和 b, 实现删除位于 a/b 处节点的函数
 *          例如:
 *          链表 1->2->3->4->5, 假设 a/b的值为 r
 *          如果 r = 0 , 不删除任何节点;
 *          如果 r 在区间(0, 1/5]上, 删除节点1;
 *          如果 r 在区间(1/5, 2/5]上, 删除节点2;
 *          如果 r 在区间(2/5, 3/5]上, 删除节点3;
 *          如果 r 在区间(3/5, 4/5]上, 删除节点4;
 *          如果 r 在区间(4/5, 1]上, 删除节点5;
 *          如果 r >1, 不删除任何节点;
 *
 */
public class C03_1x_删除链表的中间节点和a除b处的节点 {

    /**
     * 长度为空或1, 不需要调整
     * 长度为2, 将头节点删除
     * 长度为3, 删除第2个节点
     * 长度为4, 删除第2个节点
     * 长度为5, 删除第3个节点
     * ..
     * 长度每增加2(3,5,7..) 要删除的节点就后移一个节点
     */
    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public void deleteMidNodeXX(Node head) {
        int len = 0;
        int delPoi = 0;
        Node cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (len <= 1) {
            return;
        } else if (len / 2 == 0) {
            delPoi = len / 2;
        } else {
            delPoi = len / 2 + 1;
        }

        if (delPoi == 1) {
            head = head.next;
            return;
        }

        cur = head;
        for (int i = 1; i < delPoi - 1; i++) {
            cur = cur.next;
        }
        cur = cur.next.next;

    }

    public Node removeMidNode(Node head) {
        if (head == null || head.next == null){
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    /**
     * 根据链表的长度n, 以及a与b的值,决定删除节点:
     *   计算:  double r = ((double)(a * n) /((double)b)
     *          r 向上取整
     */
    public Node removeByRatic(Node head, int a, int b) {
        if (a < 1 || a > b) {
            return head;
        }
        int n = 0;
        Node cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        n = (int) Math.ceil(((double) (a * n)) / (double) b);
        if (n == 1) {
            head = head.next;
        }
        if (n > 1) {
            cur = head;
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }


}
