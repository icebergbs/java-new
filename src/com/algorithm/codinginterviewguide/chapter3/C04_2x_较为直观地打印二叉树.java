package com.algorithm.codinginterviewguide.chapter3;

/**
 *
 * 题目：
 *     二叉树可以用常规地三种变量结果来描述其结构，但是不够直观，尤其是二叉树中有重复值地时候，仅
 *     通过三种遍历地结果来构造二叉树地真实结构更是难上加难，有时则根本不可能。
 *     给定一棵二叉树地头节点head, 已知二叉树节点值地类型为32为整数，请实现一个打印二叉树地函数，
 *     可以直观地展示树的形状，也便于画出真实的结构。
 *
 * @author bingshan
 * @date 2022/12/25 20:21
 */
public class C04_2x_较为直观地打印二叉树 {

/*
    这是一道较开放的题目，面试者不仅要设计出符合要求且不会产生歧义的打印方式，
还要考虑实现难度，在面试时仅仅写出思路必然是不满足面试要求的。本书给出了一种符合
要求且代码量不大的实现，希望读者也能实现并优化自己的设计。
    1. 设计打印的样式。实现者首先应该解决的问题是用什么样的方式来无歧义地打印二
叉树。比如， 如图3-4 所示。
    对如图3-4所示的二叉树，本书设计的打印样式如图3-5所示。
    下面解释一下如何看打印结果。首先，二叉树大概的样子是把打印结果顺时针旋转90';
怎么清晰地确定任何一个节点的父节点呢？如果一个节点打印结果的前者和后缀都有H, 说明这个节点是
头节点，当然不存在父节点。
    如果一个节点打印的前缀和后缀都有“V", 表示父节点在该节点所在列的前一列，在该节点所在行的下方，
并且是离该节点最近的节点。
    如果一个节点打印结果的前缀与后缀都有”^", 表示父节点在该节点所在列的前一列，在该节点所在行的上方，
并且是离该节点最近的节点。
    2. 一个需要重点考虑的问题 -- 规定节点打印时占用的统一长度。我们必须规定一个节点在打印时到底占多长。
试想一下，有些节点长度很短，比如“1”“2”， 而有些节点本身长度很长， 比如 “43343443” “78788788”等，那么，
如果不规定一个统一长度，打印一个长短交替的二叉树时必然会出现格式对不齐的问题。
    在Java中，整形值占用长度最长的值是Integer.MIN_VALUE（即-2147483648）， 占用长度为11， 加上前缀和后缀
之后占用长度为13.为了在打印之后更好的区分，再把前面和后面加上两个空格，总共占用17.
    至此，我们在打印时每个节点占用长度都为17，不足，前后都用空格补齐。
    3. 确定了打印的样式，规定了占用长度的标准，最后来解释具体的实现。 打印的整体结合了二叉树先右子树、在根节点、
最后左子树的递归遍历过程。
    如果递归到一个节点，首先遍历它的右子树。右子树遍历结束后，回到这个节点。
    如果这个节点所在层为1，那么先打印1*17个空格(不换行)，然后开始制作该节点的打印内容，这个内容当然包括节点的值，
以及确定的前后缀字符。如果该节点是其父节点的右孩子，前后缀为“V", 如果是其父节点的左孩子，前后缀为”^",
如果是头节点，前后缀为“H". 最后在前后分别贴上数量几乎一致的空格，打印这个内容后换行。
    最后进行左子树的遍历过程。

 */

    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if(head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height + len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        head.left = left;
        head.right = right;
        printTree(head);
    }
}
