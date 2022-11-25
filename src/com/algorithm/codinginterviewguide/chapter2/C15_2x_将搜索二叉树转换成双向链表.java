package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      对二叉树的节点来说，有本身的值域，有指向左孩子节点和右孩子节点的两个指针； 对双
 *    向链表的节点来说，有本身的值域，有指向上一个节点和下一个节点的指针。在结构上，两种
 *    结构有相似性，现在有一颗搜索二叉树，请将其转换为一个有序的双向链表。
 *         二叉树如图 2-11
 *
 *      这颗搜索二叉树转换后的双向链表从头到尾依次是1-9。 对每一个节点来说，原来的right
 *    指针等价于转换后的next指针，原来的left指针等级于转换后的last指针，最后返回转换后
 *    的双向链表头节点。
 *
 */
public class C15_2x_将搜索二叉树转换成双向链表 {

    class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    public Node treeToDoubleList(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        //找head
        Node head = null;
        while (cur.left != null) {
            cur = cur.left;
        }
        head = cur;

        while (cur != null) {
            if (cur.left != null) {
                cur = cur.left;
            }

        }
    }
 


}
