package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *  著名的犹太历史学家Josephus有过一下故事：
 *      在罗马人占领乔塔帊特后，39个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，
 *      于是决定了一种自杀方式，41人排成一个圆圈，由第一个人开始报数，报导3的人就自杀，然后再由下一个人重新报1，
 *      报数到3的人再自杀，这样依次下去，直到剩下最后一个人时，那个人可以自由选择自己的命运。
 *
 *   现在请用单向环形链表描述该结构并呈现自杀过程。
 *   输入： 一个环形单向链表的头节点head和报数的值m
 *   返回： 最后生存下来的节点，且这个节点自己形成单向环形链表,其他节点都删除。
 *
 *  进阶问题：
 *      如果链表节点数为N, 想在时间复杂度为O(N)完成原问题的要求。该怎么实现。
 *
 */
public class C06_1x_环形单链表的约瑟夫问题 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public Node josephusKill(Node head, int m) {
        if (m < 1) {
            return null;
        }
        if (head == null || head.next == null) {
            return head;
        }
        if (m == 1) {
            Node parentNode = null;
            Node curNode = head;
            while (curNode.next != head) {
                parentNode = curNode;
                curNode = curNode.next;
            }
            parentNode.next = null;
            curNode.next = null;
            return curNode;
        }

        int num = 1;
        Node parantNode = null;
        Node curNode = head;
        while (curNode.next != null && curNode.next != curNode) {
            if (num == m) {
                //删除节点
                parantNode.next = curNode.next;
                curNode = parantNode.next;
                num = 1;
            } else {
                parantNode = curNode;
                curNode = curNode.next;
            }
            num++;
        }
        return curNode;
    }

    /**
     *普通解法：
     *  1. 如果链表为空或者链表节点数为1， 或者m的值小于1， 则不用调整直接返回
     *  2. 在环形链表中遍历每个节点，不断转圈，不断让每个节点报数
     *  3. 当报数到达m时，就删除当前报数的节点
     *  4. 删除节点后， 把剩下的节点继续连城环状，继续
     *  5. 不停地删除，直到环形链表中只剩一个节点，过程结束
     *
     *  没删除一个节点，都需要遍历m次，一共需要删除的节点数为 n-1, 所以时间复杂度为 O(n*m)
     * @param head
     * @param m
     * @return
     */
    public Node josephusKilll(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }


/*
  举个例子，环形链表为： 1->2->3->4->5->1, 这个链表节点数为n=5, m=3.
  通过不断删除的方式，最后节点4会活下来。但是我们可以不用一直删除的方式，而是用进
阶的方法，根据n与m的值，直接算出是第4个节点最终会活下来，接下来找到节点4即可。
  到底怎么直接算出来？ 首先，如果环形链表节点数为n, 我们做如下定义：从这个环形
链表的头节点开始编号，头节点编号为1，头节点的下一个节点编号为2，...，最后一个节点
编号为n。然后考虑如下问题：
  最后只剩下一个节点，这个幸存节点在只由自己组成的环中编号为1，记为Num(1)=1;
  在由两个节点组成的环中，这个幸存节点的编号是多少呢？假设编号是Num(2);
  ......
  在由i-1个节点组成的环中，这个幸存节点的编号是多少呢？假设编号是Num(i-1);
  在由i个节点组成的环中，这个幸存节点的编号是多少呢？假设编号是Num(i);
  ......
  在由n个节点组成的环中，这个幸存节点的编号是多少呢？假设编号是Num(n);
  我们已经知道Num(1)=1,如果再确定Num(i-1)和Num(i)到达是什么关系，就可以逐渐求出
Num(n)了，下面是求解过程。
 */

    /**
     * @param head
     * @param m
     * @return
     */
    public Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1;  //tmp -> list size
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);  // tmp -> service nod position
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    private int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1);
    }
}
