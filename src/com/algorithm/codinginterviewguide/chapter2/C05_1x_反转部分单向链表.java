package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *    给定一个单向链表的头节点head, 以及两个整数from 和 to, 在单向链表上把第 from 个节点到第 to个节点这一部分进行反转
 *
 *    例如：
 *      1->2->3->4->5->null,  from=2， to=4
 *      调整结果为： 1->4->3->2->5->null
 *      再如：
 *          1->2->3->null,  from=1, to=3
 *       调整结果为： 3->2->1->null
 *
 *  要求：
 *     1. 如果链表长度为N, 时间复杂度要求为 O(N), 额外空间复杂度为O(1)
 *     2. 如果不满足 1<=from<=to<=N, 则不用调整
 *
 *
 */
public class C05_1x_反转部分单向链表 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 反转单向链表
     */

    public Node reservePartxx(Node head, int from , int to) {
        int len = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        if (from >=1 && to <= len) {
            int i = 1;
            Node newHead = head;
            while (i < from) {
                newHead = newHead.next;
                i++;
            }

            Node pre = null;
            Node next = null;
            while (newHead != null && i <= to) {
                next = newHead.next;
                newHead.next = pre;
                pre = newHead;
                newHead = next;
                i++;
            }
            if (next != null) {
                pre.next = next;
            }

        }
        return head;
    }

    /**
     * 解答：
     *   本题肯能存在换头的问题， 比如第二个例子，所以函数应该返回调整后的新头节点：
     *      1. 先判断是否满足 1<=from  <=to <=N, 如果不满足直接返回原来的头节点
     *      2. 找到第from - 1个节点fPre 和第 to + 1个节点tPos, 把反转的部分先反转，然后正确地连接fPre和tPos
     *          例如： 1>2>3>4>null, 假设fPre为节点1，tPos为节点4， 要反转的部分为2>3.
     *                先反转成3>2, 然后fPre连向节点3， 节点2连向tPos, 就变成了1>3>2>4>null
     *      3. 如果fPre为null, 说明反转部分是包含头节点的， 则返回新的头节点； 如果fPre不为null, 则返回旧的头节点。
     *
     * @param head
     * @param from
     * @param to
     * @return
     */
    public Node reservePart(Node head, int from , int to) {
        int len = 0;
        Node node1 = head;
        Node fPre = null;
        Node tPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from -1 ? node1 : head;
            tPos = len == to - 1 ? node1 : head;
        }

        if (from > to || from < 1 || to > len) {
            return head;
        }

        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = tPos;
        Node next = null;
        while (node2 != tPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;
    }



}
