package com.algorithm.codinginterviewguide.chapter2;

import java.util.HashMap;
import java.util.Stack;

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

    public Node createSumxxx(Node head1, Node head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        int value1 = 0;
        while (head1 != null) {
            value1 = value1 * 10 + head1.value;
        }
        int value2 = 0;
        while (head2 != null) {
            value2 = value2 * 10 + head2.value;
        }
        value1 = value1 + value2;
        Node res = null;
        while (value1 / 10 != 0) {
            Node node = new Node(value1 % 10);
            node.next = res;
            res = node;
        }
        return res;
    }

/*
  一种实现方式是将两个链表先算出来各自代表的整数值，然后让两个整数相加，最后将这个整数转换成链表。
这种方式有可能两个比较大的链表，相加之后的是一个很大的整数。 因此，转成系统中的int类型时可能会溢出，
所以不推荐使用这种方式。

  方法一: 利用栈结构
  1. 将两个链表分别从左到右遍历，遍历过程中将值压栈，分别为s1和s2
  2. 将s1和s2同步弹出，这样就相当于两个链表从地位到高位依次弹出。这个过程中生成相加链表即可，同时需要
     关注每一步是否有进位， 用ca表示。
  3. 当s1和s2都为空时，还要关注一下进位信息是否为1， 如果为1.还要生成一个节点值为1的新节点，
     记为new4,令new4.next=new3
  4. 返回新生成的结构链表即可。

 */

    /**
     * 方法一: 利用栈结构
     * @param head1
     * @param head2
     * @return
     */
    public Node addLists1(Node head1, Node head2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (head1 != null) {
            s1.push(head1.value);
            head1 = head1.next;
        }
        while (head2 != null) {
            s1.push(head2.value);
            head2 = head2.next;
        }
        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            n1 = s1.isEmpty() ? 0 : s1.pop();
            n2 = s2.isEmpty() ? 0 : s2.pop();
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(1);
            node.next = pre;
        }
        return node;
    }

/*
  方法二：利用链表的逆序求解，可以节省用栈的空间
  1. 将两个链表逆序，这样就可以依次得到从地位到高位的数字。
  2. 同步遍历两个逆序后的链表，在这个过程中生成相加链表即可，同时需要关注每一步是否有进位，
     用ca表示。具体过程与方法一的步骤2相同
  3. 当两个链表都遍历完成后，还要关注进位信息是否为1。
  4. 将两个逆序的链表在逆序一次，即调整成原来的样子。
  5. 返回新生成的结果链表。
 */
    /**
     * 方法二: 利用栈结构
     * @param head1
     * @param head2
     * @return
     */
    public Node addLists2(Node head1, Node head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);

        int ca = 0;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        Node node = null;
        Node pre = null;
        Node c1 = head1;
        Node c2 = head2;
        while (c1 != null || c2 != null) {
            n1 = c1 == null ? 0 : c1.value;
            n2 = c2 == null ? 0 : c2.value;
            n = n1 + n2 + ca;
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
            ca = n / 10;
            c1 = c1 != null ? c1.next : null;
            c2 = c2 != null ? c2.next : null;
        }
        if (ca == 1) {
            pre = node;
            node = new Node(n % 10);
            node.next = pre;
        }
        reverseList(head1);
        reverseList(head2);
        return node;
    }

    /**
     * 反转链表
     *    FUNCTION-******
     * @param head
     * @return
     */
    private Node reverseList(Node head) {
        if (head == null) {
            return null;
        }
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
