package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *     给定一共链表的头节点head, 请判断该链表是否为回文结构
 *
 *     例如：
 *          1->2->1, 返回true
 *          1->2->2->1, 返回true
 *          15->6->15, 返回true
 *          1->2->3, 返回false
 *
 *      进阶：
 *          如果链表长度为N, 时间复杂度到达O(N), 额外空间复杂度达到O(1)
 *
 */
public class C07_1x_判断一个链表是否为回文结构 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


}
