package com.algorithm.codinginterviewguide.chapter3;

/**
 *
 * 题目：
 *     二叉树被记录成文件的过程叫作二叉树的序列化，通过文件内容重建原来二叉树的过程叫作二叉树的反序列化。
 *     给定一棵二叉树的头节点head, 已知二叉树节点值的类型为32位整型。
 *     请设计一种二叉树序列化和反序列化的方案，并于代码实现
 *
 * @author bingshan
 * @date 2022/12/25 20:21
 */
public class C05_1x_二叉树的序列化和反序列化 {
    class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

/*
    方法一： 通过先序遍历实现序列化和反序列化
    首先假设序列化的结果字符串为str, 初始时str="".
    先序遍历二叉树，如果遇到null节点，就在str的末尾加上”#！“， ”#“表示这个节点为空，
节点值不存在， ”！“表示一个值的结束； 如果遇到不为空的节点，假设节点值为3，就在str的末尾
加上”3！”。 比如图3-6所示的二叉树。
    最后的结果字符串str= 12!3!#!#!#!
    为什么在每个节点值的后面都加上“！”？ 因为如果不标记一个值的结果，最后产生的结果会有歧义，
如图3-7所示。
    如果不加结束符，那么图3-6和图3-7的先序遍历序列化结果都是 123###，也就是说，生成的字符串并
不代表唯一的树。
 */
    public String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }
}
