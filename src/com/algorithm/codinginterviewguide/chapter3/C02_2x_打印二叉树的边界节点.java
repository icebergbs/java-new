package com.algorithm.codinginterviewguide.chapter3;

import java.util.Stack;

/**
 *  题目：
 *      给定一棵二叉树的头节点head, 按照如下两种标准分别实现二叉树边界节点的逆时针打印。
 *      标准一：
 *          1.头节点为边界节点。
 *          2.叶子节点为边界节点。
 *          3.如果节点在其所在的层中是最左或最右的，那么也是边界节点。
 *      标准二：
 *          1.头节点为边界节点。
 *          2.叶节点为边界节点。
 *          3.树左边界延申下去的路径为边界节点。
 *          4.树右边界延申下去的路径为边界节点
 *
 *      例如： 如图3-2所示的树
 *          图3-2
 *      按标准一打印结果 ： 1，2，4，7，11，13，14，15，16，12，10，6，3
 *      按标准二打印结果 ： 1，2，4，7，13，14，15，16，10，6，3
 *
 *   要求：
 *      1. 如果节点数为N, 两种标准实现的时间复杂度要求都为O(N), 额外的空间复杂度要求都为O(h), h为二叉数的高度
 *      2. 两种标准都要求逆时针顺序且不重复打印所有的边界节点。
 *
 * @author bingshan
 * @date 2022/12/6 12:28
 */
public class C02_2x_打印二叉树的边界节点 {


    class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

/*
    标准一：
    1. 得到二叉树每一层上最左和最右的节点。以题目的例子来说，这个记录如下：
                最左   最右
       第1层     1     1
       第2层     2     3
       第3层     4     6
       第4层     7     10
       第5层     11    12
       第6层     13    16
     2. 从上到下打印所以层中的最左节点。
     3. 先序遍历二叉树，打印那些不属于某一层最左或最右的节点，但同时又是叶节点的节点。
        14，15
     4. 从下到上打印所有层中的最右节点，但节点不能即是最左节点，又是最右节点。
        16，12，10，6，3

 */
    public void printEdge(Node head) {
        if (head == null) {
            return;
        }
        int height = getHeight(head, 0);
        Node[][] edgeMap = new Node[height][2];
        setEdgetMap(head, 0, edgeMap);
        //打印左边界
        for (int i = 0; i != edgeMap.length; i++) {
            System.out.println(edgeMap[i][0].value + " ");
        }
        //打印不是左边界，也不是右边界的叶子节点
        printLeafNotInMap(head, 0, edgeMap);
        // 打印右边界，但不是左边界的节点
        for (int i = edgeMap.length - 1; i != -1; i--) {
            if (edgeMap[i][0] != edgeMap[i][1]) {
                System.out.println(edgeMap[i][1].value + " ");
            }
        }
        System.out.println();
    }

    public int getHeight(Node h, int l) {
        if (h == null) {
            return l;
        }
        return Math.max(getHeight(h.left, l + 1), getHeight(h.right, l + 1));
    }

    public void setEdgetMap(Node h, int l, Node[][] edgeMap) {
        if (h == null) {
            return;
        }
        edgeMap[1][0] = edgeMap[1][0] == null ? h : edgeMap[1][0];
        edgeMap[l][1] = h;
        setEdgetMap(h.left, l + 1, edgeMap);
        setEdgetMap(h.right, l + 1, edgeMap);
    }

    public void printLeafNotInMap(Node h, int l, Node[][] m) {
        if (h == null) {
            return;
        }
        if (h.left == null && h.right == null && h != m[l][0] && h != m[l][1]) {
            System.out.println(h.value + " ");
        }
        printLeafNotInMap(h.left, l + 1, m);
        printLeafNotInMap(h.right, l + 1, m);
    }

/*
    标准二的要求实现过程：
    1. 从头节点开始往下寻找，只要找到第一既有左孩子，又有右孩子的节点，记为h,
则进入步骤2。在这个过程中，找过的节点都打印。
       对于题目的例子来说，即打印：1， 因为头节点直接符合要求，所以打印后没有后续的寻找过程，
       直接进入步骤2.但如果二叉树如图3-3所示，此时则打印：1，2，3.节点3是从头节点开始往下第一个符合要求的。
       如果二叉树从上到下一直找到叶节点也不存在符合要求的节点，说明二叉树是棒状结构，
       那么打印找过的节点后直接返回即可。
    2. h的左子树先进入步骤3的打印过程：h的右子树再进入步骤4的打印过程；最后返回。
    3. 打印左边界的延申路径以及h左子树上所有的叶节点，具体请参看printLeftEdge方法。
    4. 打印右边界的延申路径以及h右子树上所有的叶节点，具体请参看printRightEdge方法。
 */
    public void printEdge2(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
        System.out.println();
    }

    public void printLeftEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }
        if (print || (h.left == null && h.right == null)) {
            System.out.println(h.value + "  ");
        }
        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null ? true : false);
    }

    public void printRightEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }
        printRightEdge(h.left, print && h.right == null ? true : false);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) {
            System.out.println(h.value + " ");
        }
    }

}
