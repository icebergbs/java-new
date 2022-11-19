package com.algorithm.codinginterviewguide.chapter2;

/**
 * 题目:
 *      给定一个单向链表的头节点head, 节点的值类型是整形，在给定一个整数pivot. 实现一个
 *      调整链表的函数，将链表调整为左部分都是值小于pivot的节点， 中间部分都是值等于pivot的节点，
 *      右部分都是值大于pivot的节点。除这个要求外，对调整后的节点顺序没有更多的要求。
 *
 *      例如：
 *          链表9->0->4->5->1, pivot=3
 *          调整后链表可以是 1->0->4->9->5, 0->1->9->5->4 等。 对某部分内部的节点顺序不做要求
 *
 *      进阶问题：
 *          在原问题的要求之上，在增加如下两个要求。
 *              1. 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一样
 *                  调整后 0->1->9->4->5
 *              2. 如果链表表示长度为N, 时间复杂度请到达 O(N), 额外空间复杂度达到O(1)
 *
 */
public class C08_2x_将单向链表按某值划分成左边小_中间相等_右边大的形式 {

    class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

/*
  普通解法的时间复杂度为O(N), 额外的空间复杂度为O(N),就是把链表中的所有节点放入一
个额外的数组中，然后统一调整位置的办法。
  1. 先遍历一遍链表，为了得到链表的长度，假设长度为N
  2. 生成长度为N的Node类型的数组nodeArr, 然后遍历一次链表，将节点依次放进nodeArr
中。本书这里不用LinkedList或ArrayList等Java提供的结构，因为一个纯粹的数组结构比较
利于步骤3的调整。
  3. 大小按左中右。也就是改进了快速排序中partition的调整过程，即如下代码中的
arrPartition(). 实现的具体解释请参考“数组类似partiton的调整”问题。
  4. 把nodeArr中的节点依次重连起来即可。
 */
    public Node pivot1xx(Node head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }
        int len = 0;
        Node cur =head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] nodeArr = new int[len];
        int i = 0;
        int j = len - 1;
        int tmp = nodeArr[0];
        while (i < j) {
            if (nodeArr[i] < pivot) {
                i++;
            } else {
                tmp = nodeArr[i];
                while (i < j) {
                    if (nodeArr[j] > pivot) {
                        j--;
                    } else {
                        nodeArr[i] = nodeArr[j];
                        nodeArr[j] = tmp;
                        i++;
                        j--;
                        break;
                    }
                }
            }
        }
        cur = head;
        i = 0;
        while (cur != null) {
            cur.value = nodeArr[i];
        }
        return head;
    }


    /**
     * 方法一
     */
    public Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i-1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    /**
     * FUNCTION-******  改进了快速排序中partition的调整过程
     *
     * @param nodeArr
     * @param pivot
     */
    private void arrPartition(Node[] nodeArr, int pivot) {
        int samll = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++samll, index++);
            } else if(nodeArr[index].value == pivot){
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    public void swap(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }

/*
  进阶解法。增加了节点顺序要求，同时时间复杂度为O(N),额外空间复杂度为O(1),说明
实现时只能使用有限的几个变量来完成所有的调整。
  1. 将原链表的所有节点依次划分进三个链表，三个链表分别为small代表左部分，equal
代表中间部分，big代表右部分。
  例如，链表7->9->1->8->5->2->5, pivot=5, 在划分后：
    small: 1->2->null
    equal: 5->5->null
    big: 7->9->8->null
  2. 将small、equal、big三个链表重新串起来。
  3. 整个过程需要特别注意对null节点的判断和处理。
  进阶解法主要还是考察面试者利用有限几个变量调整链表的代码实现能力。
 */

    /**
     * 方法二
     */
    public Node listPartition2(Node head, int pivot) {
        Node sH = null; //小的头
        Node sT = null; //小的尾
        Node eH = null; //相等的头
        Node eT = null; //相等的尾
        Node bH = null; //大的尾
        Node bT = null; //大的尾
        Node next = null; // 保存下一个节点

        //所有节点分进三个链表中
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else  {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot){
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        // 小的和相等的重新连接
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }
        //所有的重新连接
        if (eT != null) {
            eT.next = bH;
        }
        return sH != null ? sH : eH != null ? eH : bH;
    }


}
