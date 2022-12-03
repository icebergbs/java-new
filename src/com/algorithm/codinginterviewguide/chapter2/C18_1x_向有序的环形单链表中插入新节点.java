package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *       一个环形单链表从头节点head开始不降序，同时由最后的节点指回头节点。
 *       给定这样一个环形单链表的头节点head和一个整数num, 请生成节点值为num的新节点，
 *       并插入到这个环形链表中，保证调整后的链表依然有序。
 */
public class C18_1x_向有序的环形单链表中插入新节点 {

    class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public Node addNewNumNode(Node head, int num) {
        Node newNode = new Node(num);
        if (head == null) {
            return newNode;
        }
        if (head.value > num) {
            Node cur = head;
            while (cur.next != head) {
                cur = cur.next;
            }
            cur.next = newNode;
            newNode.next = head;
            head = newNode;
            return head;
        }
        Node pre = null;
        Node cur = head;
        while (cur.next != head) {
            if(cur.value > num) {
                break;
            }
            // xxxxx  pre
            cur = cur.next;
        }
        if (pre == null) {
            newNode.next = head;
            head.next = newNode;
        } else {
            newNode.next = cur;
            pre.next = newNode;
        }
        return head;
    }

/*
    时间复杂度为O(N), 额外空间复杂度为O(1)的方法。
    1. 生成节点值为num的新节点，记为node
    2. 如果链表为空，让node自己组成环形链表，然后直接返回node.
    3. 如果链表不为空，令变量pre=head, cur=head.next, 然后令pre和cur同步移动下去，
如果遇到pre的节点值小于或等于num,并且cur的节点值大于或等于num, 说明node应该在pre节点和
cur节点之间插入，插入node，然后返回head即可。例如，链表1->3->4->1->..., num=2。应该
把节点值为2的节点插入到1和3之间，然后返回头节点。
    4. 如果pre和cur转了一圈，这期间都没有发现步骤3所说的情况，说明node应该插入到头节点
的前面，这种情况之所有会发生，要么是因为node节点的值比链表中每个节点的值都大，要么是因为node
的值比链表中每个节点的值都小。
    5. 如果node节点的值比链表中每个节点的值都大，返回原来的头节点； 如果node节点的值比链表
中每个节点的值都小，应该把node作为链表新的头节点返回。
 */
    public Node insertNum(Node head, int num) {
        Node node = new Node(num);
        if (head == null) {
            node.next = node;
            return node;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (pre.value <= num && cur.value >= num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        pre.next = node;
        node.next = cur;
        return head.value < num ? head : node;
    }

}
