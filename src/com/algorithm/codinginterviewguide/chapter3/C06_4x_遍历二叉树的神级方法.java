package com.algorithm.codinginterviewguide.chapter3;

/**
 *
 * 题目：
 *     给定一棵二叉树的头节点head, 完成二叉树的先序、中序、后序遍历。
 *     如果二叉树的节点数为N, 则要求时间复杂度为O(N), 额外空间复杂度为O(1)
 *
 * @author bingshan
 * @date 2022/12/25 20:21
 */
public class C06_4x_遍历二叉树的神级方法 {

    class Node {
        public int value;
        public  Node left;
        public  Node right;
        public Node(int data) {
            this.value = data;
        }
    }

/*
    本题真正的难点在于对复杂度的要求。之前的递归和非递归的方法实现遍历二叉树，两种的
额外空间都与树的高度相关，所以空间负载度为O(h)。
    如果完全不要栈结构能完成三种遍历吗? 答案是可以，方法是使用二叉树节点中大量指向null的
指针，本题实际上就是大名鼎鼎的的 Morris遍历，由Jsoeph Morris于1979年发明。
    首先来看普通的递归和非递归解法，其实都使用了栈结构。为什么从下层回到上层会如此之难？
因为二叉树的结构如此，没有指向父节点的指针，所以从下层到上层需要用栈结构辅助完成。
    Morris遍历的实质就是避免用栈结构，而是让下层到上层有指针，具体是通过让底层节点指向null
的空闲指针指回上层的某个节点，从而完成下层到上层的移动。  我们知道，二叉树上的很多节点
都有大量的空闲指针，比如，某些节点没有右孩子节点，那么这个节点的right指针就指向null, 我们称为
空闲状态， Morris遍历正是利用了这些空闲指针。

    Morris遍历的过程。
    假设当前节点为cur, 初始时cur就是整颗树的头节点，根据以下标准让cur移动：
    1. 如果cur为null,则过程停止，否则继续下面的过程。
    2. 如果cur没有左子树，则让cur向右移动，即令 cur = cur.right。
    3. 如果cur有左子树，则找到cur左子树上最右的节点，记为mostRight。
       1) 如果mostRight的right指针指向null, 则令mostRight.right = cur, 也就是让
          mostRight的right指针指向当前节点，然后让cur向左移动，即令cur = cur.left
       2) 如果mostRight的right指针指向cur, 则令mostRight.right = null, 也就是让
          mostRight的right指针指向null, 然后让cur向右移动，即令cur=cur.right

    以上标准并不复杂，下面用例子来展示遍历过程，假设一棵二叉树如图3-9所示。
    1. 初始时cur来到节点4， cur此时有左子树，所以根据刚才介绍的标准，找到cur的左子树最右节点(即节点3)，
       发现节点3的右指针是指向空的，那么让其指向cur, 树被调整成如图3-10所示的样子，然后cur向左移动到节点2。
    2. cur来到节点2， cur 此时有左子树，找到cur的左子树最右节点(即节点1)， 发现节点1的右指针是指向空的，
       那么让其指向cur, 树被调整成如图3-11所示的样子，然后cur向左移动来到节点1。
    3. cur来到节点1， cur 此时没有左子树， 根据标准令cur向右指针方向移动，所以cur回到了节点2
    4. cur来到节点2，cur 此时有左子树，找到cur的左子树最右节点(即节点1)， 发现节点1的右指针是指向cur,根据
       标准让其指向null, 树被调整回如图3-10所示，然后根据标准，cur向右指针方向移动，所以cur来到了节点3
    5. cur来到节点3， cur 此时没有左子树， 根据标准令cur向右指针方向移动，所以cur回到了节点4
    6. cur来到节点4， cur 此时有左子树，找到cur的左子树最右节点(即节点3)， 发现节点3的右指针是指向cur，
       那么让其指向null, 树被调整成如图3-9所示的样子，然后根据标准，cur向右指针方向移动，所以cur来到了节点6
    7. 初始时cur来到节点6， cur此时有左子树，找到cur的左子树最右节点(即节点5)，发现节点5的右指针是指向null，
       那么让其指向cur, 树被调整成如图3-12所示的样子，然后cur向左移动到节点5。
    8. cur来到节点5， cur 此时没有左子树， 根据标准令cur向右指针方向移动，所以cur回到了节点6
    9. cur来到节点6， cur 此时有左子树，找到cur的左子树最右节点(即节点5)， 发现节点5的右指针是指向cur，
       那么让其指向null, 树被调整成如图3-9所示的样子，然后根据标准，cur向右指针方向移动，所以cur来到了节点7
    10. cur来到节点7， cur 此时没有左子树， 根据标准令cur向右指针方向移动，所以cur来到null
    11. cur为空， 过程停止
    以上步骤严格按照cur移动标准，cur移除到达的节点为：4、2、1、2、3、4、6、5、6、7 ；我们将这个序列叫Morris序
    可以看出，在一棵二叉树中，对于有左子树的节点都可以到达两次（4、2、6）, 对于没有左子树的节点都只会到达一次。
对于任何一个只能到达一次的节点X, 接下来cur要么跑到X右子树上，要么返回上级。而对于任何一个能够到达两次的节点Y,在第一次
到达Y之后cur都会先去Y的左子树转一圈，然后会第二次来到Y, 接下来cur要么跑到Y的右子树上，要么就返回上级。
同时，对于任何一个能够到达两次的节点Y, 是如何知道此时的cur是第一次来到Y还是第二次来到Y呢？
    如果Y的左子树上的最右节点的指针（mostRight.right)是指向null，那么此时cur就是第一次到达Y; 如果mostRight.right是指向Y
的， 那么此时cur就是第二次到达Y、这就是Morris遍历和Morris序的实质。
    实现如下：
 */
    public void morris(Node head) {
         if (head == null) {
             return;
         }
         Node cur = head;
         Node mostRight = null;
         while (cur != null) {
             mostRight = cur.left;
             if (mostRight != null) { //如果当前cur有左子树
                 //找到cur左子树上最右的节点
                 while (mostRight.right != null && mostRight.right != cur) {
                     mostRight = mostRight.right;
                 }
                 // 从上面的while里出来后，mostRight就是cur左子树上最右的节点
                 if (mostRight.right == null) { //如果 mostRight.right指向null
                     mostRight.right = cur; //让其指向cur
                     cur = cur.left; // cur 向左移动
                     continue; //回到最外层的while， 继续判断cur的情况
                 } else { // 如果mostRight.right是指向cur的
                     mostRight.right = null; //让其指向null
                 }
             }
             // cur如果没有左子树，cur向右移动
             //或者cur左子树上最右节点的右指针是指向cur的，cur向右移动
             cur = cur.right;
         }
    }
/*
    以上只使用了有限几个变量，额外空间复杂度肯定是O(1)。
    但是每次来到一个有左子树的节点时，都要去遍历这个节点左子树的右边界，那么时间复杂度还是O(N)吗？依然是。
    简单的证明，请看图3-13
    根据Morris遍历的过程，所有需要遍历的右边界如下：
    到达节点1两次，每次遍历其左子树的右边界：2->5->11
    到达节点2两次，每次遍历其左子树的右边界：4->9
    到达节点3两次，每次遍历其左子树的右边界：6->13
    到达节点4两次，每次遍历其左子树的右边界：8
    到达节点5两次，每次遍历其左子树的右边界：10
    到达节点6两次，每次遍历其左子树的右边界：12
    到达节点7两次，每次遍历其左子树的右边界：14
    可以看出，所有右边界的所有节点数量为O(N)，每条右边界都遍历两次，那么遍历所有节树右边界的总代价为O(N)。
因此，Morris 遍历的时间复杂度还是O(N)
 */

/*
    根据Morris遍历，加工出先序遍历。
    1. 对于cur只能到达一次的节点(无左子树的节点)，cur 到达时直接打印
    2. 对于cur可以到达两次的节点(有左子树的节点)，cur 第一次到达时打印，第二次到不达印。
    比如，展示流程中 cur 依次达到的顺序 (Morris 序)为:4、2、1、2、3、4、6、5、6、7
    根据加工出先序遍历的规则，将依次打印:4、2、1、3、6、5、7，这就是先序遍历。
    序遍历的代码请看如下的 morrisPre方法， morrisPre方法就是 morris 方法的简单改写。
 */
    public void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.value + " "); //打印行为
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.println(cur.value + " "); //打印行为
            }
            cur = cur.right;
        }
        System.out.println();
    }

/*
    中序遍历的代码请看如下morrisin方法，也是 morris 方法的简单改写。
 */
    public void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.println(cur.value + " "); // 打印行为
            cur = cur.right;
        }
        System.out.println();
    }

/*
    后序遍历的实现，其实也是Morris遍历的改写， 但包含稍微复杂的调整过程。
    根据Morris遍历，加工出后序遍历。
    1. 对于cur只能到达一次的节点（无左子树的节点），直接跳过，没有打印行为。
    2. 对于cur可以到达两次的任何一个节点（有做只是的节点）X, cur第一次到达X时没有打印行为；
       当第二次到达X时，逆序打印X左子树的右边界。
    3. cur遍历完成后，逆序打印整颗树的右边界。
    以图3-9来举例说明后序遍历的打印过程，这颗二叉树的Morris序为：4，2，1，2，3，4，6，5，6，7
    当第二次到达2时，逆序打印节点2左子树的右边界：1
    当第二次到达4时，逆序打印节点4左子树的右边界：3，2
    当第二次到达6时，逆序打印节点6左子树的右边界：5
    cur遍历完成后，逆序打印整颗树的右边界：7，6，4
    可以看到这个顺序就是后序遍历的顺序。但是我们应该如何实现逆序打印一棵树的右边界？
因为整个过程的额外空间复杂度要求是O(1), 所以逆序打印一棵右边界的过程中，是不能申请额外的数据结构的。
为了更好地说明整个过程，下面举一个右边界比较长的例子，如图3-14所示。
    假设cur第二次到达了A, 并且要逆序打印节点A左子树的右边界，首先E.R指向null,然后将右边界逆序调整成
如图3-15所示，整个过程类似单链表的逆序操作。
    这样我们就可以从节点E开始，依次通过每个节点的right指针逆序打印整个左边界。
在打印完B后，把右边界再逆序一次，调回来即可。
    Morris后序遍历的具体实现，代码请看如下morrisPos方法，
 */
    public void morroisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    public void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value + " ");
            cur = cur.right;
        }
    }

    public Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }



}

