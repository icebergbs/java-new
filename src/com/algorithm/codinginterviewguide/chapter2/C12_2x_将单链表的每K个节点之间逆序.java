package com.algorithm.codinginterviewguide.chapter2;

import java.util.Stack;

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

    public Node reverseListGroupKxx(Node head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        Node left = head;
        Node right = head;
        Node pre = null;
        Node next = null;
        while (right != null) {
            len++;
            if (len == k) {
                next = right.next;
                reverseListxx(left, right);
                pre.next = right;
                left.next = next;

                pre = left;
            }
        }
        return head;
    }



    /**
     *
     * @param head
     * @return
     */
    private void reverseListxx(Node head, Node end) {
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
/*
    方法一： 时间复杂度为O(N), 额外的空间复杂度为O(K)
    利用栈结构
    1. 从左到右遍历链表，如果栈的大小不等于k, 就将节点不断压入栈中
    2. 当栈的大小第一次达到k时，说明第一次凑齐了k个节点节点进行逆序，从栈中依次弹出这些节点
，并根据弹出的顺序重新连接，这一组逆序完成后，需要记录一下新的头部，同时第一组的最后一个节点
（原来是头节点）应该连接下一个节点。
    3. 步骤二之后，当栈的大小每次到达K时，逆序节点。这一组逆序完成后，该组的第一个节点（原来
是该组最后一个节点）应该被上一组的最后一个节点连接上，这一组的最后一个节点（原来是该组第一个节点）
应该连接下一个节点，然后继续去凑下一组，直到链表都被遍历完。
    4. 最后应该返回newHead,作为链表新的头节点。
 */
    /**
     *  方法一： 具体实现
     * @param head
     * @param k
     * @return
     */
    public Node reverseKNode1(Node head, int k) {
        if (k > 2 ) {
            return head;
        }
        Stack<Node> stack = new Stack<>();
        Node newHead = head;
        Node cur = head;
        Node pre = null;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                pre = resign1(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }

    public Node resign1(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if (left != null) {
            left.next = cur;
        }
        Node next = null;
        while (!stack.isEmpty()) {
            next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }



/*
    用变量记录每一组开始的第一个节点和最后一个节点，然后直接按逆序调整，把这一组的节点都逆序。
和方法一一样，同样需要注意第一组节点的特殊处理，以及之后的每个组在逆序重连之后，需要让该组的
第一个节点（原来是最后一个节点）被之前组的最后一个节点连接上，将该组的最后一个节点（原来是第一
个节点）连接下一个节点。
 */

    /**
     *  方法二： 具体实现
     * @param head
     * @param k
     * @return
     */
    public Node reverseKNode(Node head, int k) {
        if (k < 2) {
            return head;
        }
        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while (cur != null) {
             next = cur.next;
             if (count == k) {
                 start = pre == null ? head : pre.next;
                 head = pre == null ? cur : head;
                 resign2(pre, start, cur, next);
                 pre = start;
                 count = 0;
             }
             count++;
             cur = next;
        }
        return head;
    }

    /**
     *  FUNCTION-****** 反转链表，逆序重连
     * @param left
     * @param start
     * @param end
     * @param right
     */
    private void resign2(Node left , Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }



}
