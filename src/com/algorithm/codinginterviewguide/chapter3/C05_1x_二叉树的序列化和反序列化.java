package com.algorithm.codinginterviewguide.chapter3;

import java.util.LinkedList;
import java.util.Queue;

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

/*
    通过先序遍历序列化的结果字符串str, 重构二叉树的过程，即反序列化。
    把结果字符串str变成字符串类型的数组，记为values, 数组代表一棵二叉树先序遍历
的节点顺序。例如，str="12!3!#!#!#!", 生成value=["12","3","#","#","#"]

 */
    public Node reconByPreString(String preStr) {
        String[] values = preStr.split("!");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    public Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


/*
    方法二： 通过层遍历实现序列化和反序列化
    首先假设序列化的结果字符串为str, 初始时str=""。
然后实现二叉树的按层遍历，具体方法是利用队列结构，这也是宽度遍历图的常见方式，
例如，图3-8所示的二叉树。  按层遍历后str="1!2!3!4!#!#!5!#!#!#!#!
 */
    public String serialByLevel(Node head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                res += head.left.value + "!";
                queue.offer(head.left);
            } else {
                res += "#!";
            }
            if (head.right != null) {
                res += head.right.value + "!";
                queue.offer(head.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

/*
    先序遍历的反序列化就是重做先序遍历，遇到“#”就生成空节点，结束生成后续子树的过程。
    与根据先序遍历的反序列化一样，根据层做反序列化是重做层遍历，遇到“#”就生成null节点，
同时不把null节点放到队列里即可。
 */
    public Node reconByLevelString(String levelStr) {
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right = generateNodeByString(values[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return head;
    }

    public Node generateNodeByString(String val) {
        if (val.equals("#")) {
            return null;
        }
        return new Node(Integer.valueOf(val));
    }


}
