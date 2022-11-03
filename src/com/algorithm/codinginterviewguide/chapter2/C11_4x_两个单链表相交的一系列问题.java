package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      在本题中,单链表可能有环,也可能无环.给定两个单链表的头节点head1 和 head2,
 *      这两个链表可能相交,也可能不相交.
 *      请实现一个函数, 如果两个链表相交,请返回相交的第一个节点; 如果不相交,返回null
 *
 *  要求:
 *     如果链表1的长度为N, 链表2的长度为M, 时间复杂度请到达O(N+M), 额外空间复杂度请达到O(1)
 *
 *
 */
public class C11_4x_两个单链表相交的一系列问题 {

    class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

/*
  这道题需要分析的情况非常多，因为要求的额外空间复杂度为O(1),所以实现起来比较困难。
  本题可以拆分成3个子问题，每一个问题都可以作为一道独立的算法题。
  问题一： 如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null.
  问题二： 如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null.
  问题三： 如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null.
  注意：如果一个链表有环，另一个链表无环，它们是不可能相交的，直接返回null.

 */

/*
  问题一： 如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null.
  如果一个链表没有环，那么遍历链表一定可以遇到链表的终点。; 如果链表有环，那么遍历链表就
永远在环里转下去，如何找到第一个入环的节点，具体过程如下：
  1. 设置一个慢指针slow和一个块指针fast. 在开始时，slow和fast都指向链表的头节点head.
然后slow每移动一步，fast每次移动两步，在链表中遍历。
  2. 如果链表无环，那么fast指针在移动过程中一定先遇到终点，一旦fast到达终点，说明链表是
没有环的，直接返回null,表示该链表无环，当然也没有第一个入环节点。
  3. 如果链表有环，那么fast指针和slow指针一定会在环中的某个位置相遇，当fast和slow相遇时，
fast指针重新回到head的位置，slow指针不动。 接下来，fast指针从每次移动两步改为每次移动一步，
slow指针依然每次移动一步，然后继续遍历。
  4. fast指针和slow指针一定会再次相遇，并且在第一个入环的节点处相遇。 证明略。

 */

    /**
     * 问题一 实现
     * @param head
     * @return
     */
    public Node getLoopNode(Node head) {
        if (head == null || head .next == null || head.next.next == null) {
            return null;
        }

        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n2 = n2.next.next;
            n1 = n1.next;
        }
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;

    }

/*
  两个链表都无环
  问题二： 如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null.
  如果两个无环链表相交，那么从相交节点开始，一直到两个链表终止的这一段，是两个链表共享的。
  1. 链表1从头节点开始，走到最后一个节点(不是结束)， 统计链表1的长度记为len1, 同时
记录链表1的最后一个节点记为end1.
  2. 链表2从头节点开始，走到最后一个节点(不是结束)， 统计链表2的长度记为len2, 同时
记录链表2的最后一个节点记为end2.
  3. 如果 end1 != end2, 说明两个链表不相交，返回null即可； 如果end1 == end2,
说明两个链表相交，进入步骤4寻找第一个相交节点
  4. 如果链表1比较长，链表1就先走 len1-len2 步；然后两个链表一起走，一起走的过程中，
两个链表第一次走到一起的那个节点就是第一个相交的节点。

 */

    /**
     * 问题二 实现
     * @param head1
     * @return
     */
    public Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

/*
  问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null,
  假设链表1的第一个入环节点记为loop1, 链表2的第一个入环节点记为loop2.
  1. 如果loop1 == loop2, 那么两个链表的拓扑结构如
        图2-8所示
  这种情况考虑在哪里第一次相交，这就与问题二类似，只不过问题二是null作为一个链表的
终点，而这里是把loop1{loop2}作为链表的终点。
  2. 如果loop1 != loop2, 两个链表不相交的拓扑结构如图2-9所示。两个链表相交的拓扑结构
     如图2-10 所示
     如何分辨是这两种拓扑结构中的哪一种？ 进入步骤3
  3. 让链表1从loop1出发，因为loop1和之后的所以节点都在环上，所以将来一定能回到loop1,如果
回到loop1之前并没有遇到loop2,说明两个链表的拓扑结构如图2-9所示，也就是不相交，直接返回null;
如果回到loop1之前遇到了loop2，说明两个链表的拓扑结构如图2-10所示，也就是相交。因为loop1和
loop2都在两天链表上，只不过loop1是离链表1较近的节点，loop2是离链表2较近的节点，所以，此时
返回loop1或loop2都可以。


 */

}
