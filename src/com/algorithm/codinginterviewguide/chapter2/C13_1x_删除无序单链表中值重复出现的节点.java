package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *   给定一个无序单链表的头节点head, 删除其中值重复出现的节点。
 *
 *   例如： 1->2->3->3->4->4->2->1->1->null, 删除值重复的节点之后为
 *         1->2->3->4->null
 *
 *   请按以下要求实现两种方法。
 *   方法1： 如果链表长度为N, 时间复杂度达到O(N)
 *   方法2： 额外空间复杂度为O(1)
 *
 *
 */
public class C13_1x_删除无序单链表中值重复出现的节点 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }



}
