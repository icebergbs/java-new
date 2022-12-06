package com.algorithm.codinginterviewguide.chapter3;

import com.algorithm.codinginterviewguide.chapter2.C20_1x_按照左右半区的方式重新组合单链表;

/**
 *  用递归和非递归的方式，分别按照二叉树先序、中序、后续打印所有节点。
 * @author bingshan
 * @date 2022/12/6 12:28
 */
public class C01_3x_分别使用递归和非递归的方式实现二叉树的先序中序后序遍历 {

/*
    用递归的方式，直接实现，本书不做详述
 */
    class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 先序递归
     * @param head
     */
    public void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序递归
     * @param head
     */
    public void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 后序递归
     * @param head
     */
    public void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

/*
    FUNCTION-******   递归->非递归
    用递归的方法解决的问题都能用非递归的方法实现。这是因为递归方法无非是用函
数栈来保存信息，如果利用自己申请的数据结构来代替函数栈，也可以实现相同的功能。
    用非递归的方式实现二叉树的先序遍历，具体过程如下：
 */



}
