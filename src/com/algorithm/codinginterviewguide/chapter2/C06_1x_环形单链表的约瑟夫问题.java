package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *  著名的犹太历史学家Josephus有过一下故事：
 *      在罗马人占领乔塔帊特后，39个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，
 *      于是决定了一种自杀方式，41人排成一个圆圈，由第一个人开始报数，报导3的人就自杀，然后再由下一个人重新报1，
 *      报数到3的人再自杀，这样依次下去，直到剩下最后一个人时，那个人可以自由选择自己的命运。
 *
 *   现在请用单向环形链表描述该结构并呈现自杀过程。
 *   输入： 一个环形单向链表的头节点head和报数的值m
 *   返回： 最后生存下来的节点，且这个节点自己形成单向环形链表,其他节点都删除。
 *
 *  进阶问题：
 *      如果链表节点数为N, 想在时间复杂度为O(N)完成原问题的要求。该怎么实现。
 *
 */
public class C06_1x_环形单链表的约瑟夫问题 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node josephusKill(Node head, int m) {

        return null;
    }
}
