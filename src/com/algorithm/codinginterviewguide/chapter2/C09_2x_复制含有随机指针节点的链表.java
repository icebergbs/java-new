package com.algorithm.codinginterviewguide.chapter2;

import java.util.HashMap;

/**
 * 题目:
 *      一种特殊的节点类型如下：
 *          public class Node
 *          rand指针是Node类中新增的指针，可能指向链表中的任意一个节点，也可以指向null
 *      给定一个由Node节点组成的无环单链表的头节点head, 请实现一个函数完成这个链表中所有结构的复制，并返回复制的新链表的头节点。
 *      例如：
 *          1->2->3->null, 假设1 的rand指针指向3， 2的rand指针指向null, 3的rand指针指向1。
 *          复制后的链表应该也是这种结构，比如 1’->2‘->3’->null, 假设1‘ 的rand指针指向3’， 2‘的rand指针指向null, 3’的rand指针指向1‘，
 *          最后返回 1’。
 *
 * 进阶：
 *      不使用额外的数据结构，只用有限几个变量，且在时间复杂度为O(N)内完成原问题要实现的函数。
 *
 */
public class C09_2x_复制含有随机指针节点的链表 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

/*
  普通解法，时间复杂度为O(N),额外的空间复杂度为O(N),需要使用到哈希表(HashMap)结构，
  1. 从左到右遍历链表，对每个节点的复制生成的节点副本，都放入对应关系的哈希map中
  2. 再从左到右遍历链表，设置每个副本节点的next和rand指针
  3. 将1`节点作为结果返回
  */

    /**
     * 解法一
     * @param head
     * @return
     */
    public Node copyListWithRand1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

/*
  进阶解法，进阶解法只用有限的几个变量完成所有的功能。
  1. 从左到右变量链表，对每个节点cur都复制生成响应的副本节点copy, 然后把copy放
在cur和下一个要遍历的节点的中间。
     例如： 原1->2->3->null   变 1->1`->2->2`->3->3`->null
  2. 再从左到右遍历链表，再遍历时设置每一个副本节点的rand指针。
  3. 步骤2完成后，节点1，2，3，......之间的rand关系没有任何变化，节点1`,2`,3`,......
之间的rand关系也被正确设置了。此时将副本节点分离出来即可。
  4. 将1`节点作为结果返回即可。
 */

    /**
     * 进阶解法考察的依然是利用有限几个变量完成链表调整的代码能力。
     * @param head
     * @return
     */
    public Node copyListWithRand2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        Node next = null;
        //复制并链接每一个节点
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        //拆分
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = null;
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }


}
