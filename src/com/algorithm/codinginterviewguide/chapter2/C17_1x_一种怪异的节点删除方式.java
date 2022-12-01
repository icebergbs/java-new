package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *       链表节点值类型为int型, 给定一个链表中的节点node, 但不给定整个链表的头节点。
 *       如何在链表中删除node?
 *       请实现这个函数，并分析这样做会出现那些问题
 *    要求： 时间复杂度为O(1)
 */
public class C17_1x_一种怪异的节点删除方式 {

    class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public void deleteSpecial(Node node) {
        if (node == null) {
            return;
        }
        if (node.next != null) {
            Node next = node.next;
            node.value = next.value;
            node.next = next.next;
        } else {
            node = null;
        }
    }

 /*
    本题的思路很简单
    例如，链表1->2->3->null 只知道删除节点2， 而不知道头节点。
那么只需要把节点2的值变成节点3的值，然后在链表中删除节点3即可。
    这道题出现的次数很多，这么做看起来非常方便，但其实是有很大问题的。
    问题一：这样的删除方式无法删除最后一个节点，例如上面的例子，如果只知道节点3，
而不知道头节点，因为节点3是最后一个节点，根本没有下一个节点来代替节点3被删除，那么只有让节点2的
next指向null这种办法，而我们又根本找不到节点2，所以根本没法正确删除节点3。
    读者可能会问，我们能不能把节点3在内存上的区域变成null呢？这样不就相等与让节点2的next指向了null,
起到节点3被删除的效果了吗？
    不可以，null在系统中是一个特定的区域，如果想让节点2的next指针指向null, 必须找到节点2。

    问题二：这种删除方式在本质上根本就不是删除了node节点，而是把node节点的值改变，
然后删除node的下一个节点，在实际的工程中可能会带来很大问题。比如，工程上的一个节点可能代表很复杂的结构，
节点值的复制会相当复杂，或者可能改变节点值这个操作都是被禁止的； 再如，工程上的一个节点代表提供服务的
一个服务器，外界对每个节点都有很多依赖，比如，示例中删除节点2时，其实影响了节点3对外提供的服务。

  */
    public void removeNodeWired(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next == null) {
            throw new RuntimeException("cant not remove last node.");
        }
        node.value = next.value;
        node.next = next.next;
    }
 


}
