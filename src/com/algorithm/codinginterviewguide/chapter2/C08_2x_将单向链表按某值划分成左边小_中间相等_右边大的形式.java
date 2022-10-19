package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      给定一个单向链表的头节点head, 节点的值类型是整形，在给定一个整数pivot. 实现一个
 *      调整链表的函数，将链表调整为左部分都是值小于pivot的节点， 中间部分都是值等于pivot的节点，
 *      右部分都是值大于pivot的节点。除这个要求外，对调整后的节点顺序没有更多的要求。
 *
 *      例如：
 *          链表9->0->4->5->1, pivot=3
 *          调整后链表可以是 1->0->4->9->5, 0->1->9->5->4 等。 对某部分内部的节点顺序不做要求
 *
 *      进阶问题：
 *          在原问题的要求之上，在增加如下两个要求。
 *              1. 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一样
 *                  调整后 0->1->9->4->5
 *              2. 如果链表表示长度为N, 时间复杂度请到达 O(N), 额外空间复杂度达到O(1)
 *
 */
public class C08_2x_将单向链表按某值划分成左边小_中间相等_右边大的形式 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node pivot1(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        Node cur =head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] nodeArr = new int[len];
        int i = 0;
        int j = len - 1;
        int tmp = nodeArr[0];
        while (i < j) {
            if (nodeArr[i] < pivot) {
                i++;
            } else {
                tmp = nodeArr[i];
                while (i < j) {
                    if (nodeArr[j] > pivot) {
                        j--;
                    } else {
                        nodeArr[i] = nodeArr[j];
                        nodeArr[j] = tmp;
                        i++;
                        j--;
                        break;
                    }
                }
            }
        }
        cur = head;
        i = 0;
        while (cur != null) {
            cur.value = nodeArr[i];
        }
        return head;
    }




}
