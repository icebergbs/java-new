package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *       给定一个无序单链表的头节点head, 实现单链表的选择排序
 *
 *   要求： 额外空间复杂度为O(1)
 */
public class C16_1x_单链表的选择排序 {

    class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public void selectorSortxxx(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            Node max = cur;
            Node next = cur.next;
            while (next != null) {
                if (max.value < next.value) {
                    max = next;
                }
            }
            if (max != cur) {
                int value = max.value;
                max.value = cur.value;
                cur.value = value;
            }
        }
    }

/*
    选择排序： 是从未排序的部分中找到最小值，然后放在排好序部分的尾部，逐渐将未排序的部分缩小，
最后全部变成排好序的部分
    1. 找到第一个最小值节点，是整个链表的最小节点，将其设置为新的头节点，记为newHead
    2. 每次在未排序的部分找到最小值的节点，然后把这个节点从未排序的链表中删除，删除的过程当然要
       保证未排序部分的链表在结构上不至于断开。例如，2->1->3,删除节点1之后，链表应该变成2->3,
       这就要求我们应该找到要删除节点的前一个节点。
    3. 把删除的节点（也就是每次的最小值节点）连接到排好序部分的链表尾部
    4. 返回newHead
    和选择排序一样，如果链表的长度为N, 时间复杂度为O(N*N),额外的空间复杂度为O(1)
 */

    public Node selectionSort(Node head) {
        Node tail = null;
        Node cur = head;
        Node smallPre = null;
        Node small = null;
        while (cur != null) {
            small = cur;
            smallPre = getSmallestPreNode(cur);
            if (smallPre != null) {
                small = smallPre.next;
                smallPre.next = small.next;
            }
            cur = cur == small ? cur.next : cur;
            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }
        return head;
    }

    public Node getSmallestPreNode(Node head) {
        Node smallPre = null;
        Node small = head;
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < small.value) {
                smallPre = pre;
                small = cur;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }
 


}
