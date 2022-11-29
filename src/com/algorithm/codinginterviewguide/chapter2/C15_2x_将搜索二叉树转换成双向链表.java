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
/*
    方法二： 利用递归函数， 时间复杂度为O(N), 额外的空间复杂度为O(h), h为二叉树的高度
    实现递归函数process, process 的输入参数是一颗二叉树的头节点X, 功能是将以X为头的搜索
二叉树转换为一个有序双向链表，返回值是这个有序双向链表的头节点和尾节点， 结构如 ReturnType

    具体过程为先把以X为头的搜索二叉树的左子树转换为有序双向链表，并且返回有序双向链表的头和尾，
    然后把以X为头的搜索二叉树的右子树转换为有序双向链表，并且返回右子树有序双向链表的头和尾，
    接着通过X把两部分连接起来即可。

    最后不要忘记，递归函数对任何节点的要求是一样的，所以要返回此时大的有序双向链表的头和尾
 */
    class ReturnType {
        public Node start;
        public Node end;
        public ReturnType(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public Node convert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;

    }

    public ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(null, null);
        }

        ReturnType leftList = process(head.left);
        ReturnType rightList = process(head.right);
        if (leftList.end != null) {
            leftList.end.right = head;
        }
        head.left = leftList.end;

        head.right = rightList.start;
        if (rightList.start != null) {
            rightList.start.left = head;
        }

        return new ReturnType(leftList.start != null ? leftList.start : head,
                rightList.end != null ? rightList.end : head);
    }
/*
    方法二的时间、空间复杂度，可以用process递归函数发生的次数来估算， process会处理所以的子树，
子树的数量就是二叉树节点的个数，所以时间复杂度为O(N), process递归函数最多占用二叉树高度为h的栈空间，
所以额外空间复杂度为O(h)
 */




    class Node1 {
        public int value;
        public Node1 last;
        public Node1 next;
        public Node1(int data) {
            this.value = data;
        }
    }

    public Node1 treeToDoubleListxxx(Node root) {
        if (root == null) {
            return null;
        }
        Node1 head = null;
        treeHanlder(root, null, head);
        return head;
    }

    private void treeHanlder(Node mid, Node1 midNew, Node1 head) {
        midNew = new Node1(mid.value);
        if (mid.left != null) {
            Node1 last = new Node1(mid.left.value);
            treeHanlder(mid.left, last, head);

            last.next = midNew;
            midNew.last = last;
        } else {
            head = midNew;
        }

        if (mid.right != null) {
            Node1 next = new Node1(mid.right.value);
            treeHanlder(mid.right, next, head);

            next.last = midNew;
            midNew.next = next;
        }
    }
 


}
