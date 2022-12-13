package com.algorithm.codinginterviewguide.chapter3;

import com.algorithm.codinginterviewguide.chapter2.C20_1x_按照左右半区的方式重新组合单链表;

import java.util.Stack;

/**
 *  用递归和非递归的方式，分别按照二叉树先序、中序、后续打印所有节点。
 * @author bingshan
 * @date 2022/12/6 12:28
 */
public class C01_3x_分别使用递归和非递归的方式实现二叉树的先序中序后序遍历 {

/*
    用递归的方式，直接实现，本书不做详述
 */
    class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 先序递归
     * @param head
     */
    public void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 中序递归
     * @param head
     */
    public void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 后序递归
     * @param head
     */
    public void posOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

/*
    FUNCTION-******   递归->非递归
    用递归的方法解决的问题都能用非递归的方法实现。这是因为递归方法无非是用函
数栈来保存信息，如果利用自己申请的数据结构来代替函数栈，也可以实现相同的功能。
    用非递归的方式实现二叉树的先序遍历，具体过程如下：
    1. 申请一个新的栈，记为stack。 然后将头节点压入栈stack中。
    2. 从stack中弹出栈顶节点，记为cur,然后打印cur节点的值，再将节点cur的右孩
子（不为空的话）先压入stack中，最后将cur的左孩子（不为空的话）压入stack中，
    3. 不断重复步骤2，直到stack 为空，全部过程结束。
            图3-1
       节点1入栈，然后弹出并打印，接下来把节点3入栈，节点2入栈， 栈顶到栈底：2，3
       节点2弹出并打印，把节点5入栈，节点4入栈， 栈顶到栈底：4，5，3
       ......
 */
    public void preOrderUnRecur(Node head) {
        System.out.println("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value + " ");
                if (head.right != null) {
                    stack.add(head.right);
                }
                if (head.left != null) {
                    stack.add(head.left);
                }
            }
            System.out.println();
        }
    }

/*
    用非递归的方式实现二叉树的中序遍历，具体过程如下：
    1. 申请一个新的栈，记为stack。初始时，令变量cur=head
    2. 先把cur节点压入栈中，对以cur节点为头的整颗子树来说，依次把左边界压入栈
中，既不停地令cur=cur.left, 然后重复步骤2
    3. 不断重复步骤2，直到发现cur为空，此时从stack中弹出一个节点，记为node。
打印node的值，并且让cur=node.right, 然后继续重复步骤2。
    4. 当stack为空且cur为空时，整个过程停止。
 */
    public void inOrderUnRecur(Node head) {
        System.out.println("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.add(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }


    public void inOrderUnRecurxx(Node head) {
        System.out.println("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                Node cur = stack.peek();
                while (cur.left != null) {
                    stack.add(cur.left);
                    cur = cur.left;
                }
                cur = stack.pop();
                System.out.println(cur.value + " ");
                if (cur.right != null) {
                    stack.add(cur.right);
                }
            }
        }
    }


/*
    用非递归的方式实现二叉树的后序遍历有点麻烦，本书实现两种方法提供参考。
    先介绍使用两个栈实现后序遍历的过程：
    1. 申请一个栈，记为s1, 然后将头节点head压入s1中。
    2. 从s1中弹出的节点记为cur, 然后依次将cur的左孩子和右孩子压入s1中
    3. 在整个过程中，每一个从s1中弹出的节点都放进s2中。
    4. 不断重复步骤2和步骤3， 直到s1为空，过程停止。
    5. 从s2中依次弹出节点并打印，打印的顺序就是后序遍历的顺序。
 */

    public void posOrderUnRecur(Node head) {
        System.out.println("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();
    }
/*
    最后介绍只用一个栈实现后序遍历的过程，具体过程如下：
    1. 申请一个栈，记为stack, 将头节点压入stack，同时设置两个变量h和c。在整个
流程中，h代表最近一次弹出并打印的节点，c代表stack的栈顶节点，初始时h为头节点，
c为null
    2. 每次令c等于当前stack的栈顶节点，但是不从stack中弹出，此时分如下三种情况。
            2.1 如果c的左孩子不为null, 并且h不等于c的左孩子，也不等于c的右孩子，则把
        c的左孩子压入stack中。
        具体解释以下这么做的原因，首先h的意义是最近一次弹出并打印
        的节点，所以如果h等于c的左孩子或者右孩子，说明c的左子树与右子树已经打印完毕，
        此时不应该再将c的左孩子放入stack中。否则，说明左子树还没处理过，那么此时将
        c的左孩子压入stack中。
            2.2 如果条件2.1不成立，并且的右孩子不为null, h不等于c的右孩子，则把c的右
        孩子压入stack中。含义是如果h等于c的右孩子，说明c的右子树已经打印完毕，此时不
        应该再将c的右孩子放入stack中。否则，说明右自设还没处理过，此时将c的右孩子压入
        stack中。
            2.3 如果2.1 和 2.2都不成立，说明c的左子树和右子树都已经打印完毕，那么从
        stack中弹出c并打印，然后令 h=c.
    3. 一直重复步骤2， 直到stack为空，过程停止。

    用图 3-1 的例子来说明整个过程。
    节点1压入stack, 初始时h为节点1，c为null, stack从栈顶到栈底为1.
    > 令c等于stack的栈顶节点 -- 节点1， 此时步骤2的条件（1）命中，将节点2压入stack,
h为节点1，stack从栈顶到栈底为2,1
    > 令c等于stack的栈顶节点 -- 节点2， 此时步骤2的条件（1）命中，将节点4压入stack,
h为节点1，stack从栈顶到栈底为4,2,1
    < 令c等于stack的栈顶节点 -- 节点4， 此时步骤2的条件（3）命中，将节点4从stack
中弹出并打印，h为节点4，stack从栈顶到栈底为2,1
    > 令c等于stack的栈顶节点 -- 节点2， 此时步骤2的条件（2）命中，将节点5压入stack,
h为节点4，stack从栈顶到栈底为5,2,1
    < 令c等于stack的栈顶节点 -- 节点5， 此时步骤2的条件（3）命中，将节点5从stack
中弹出并打印，h为节点5，stack从栈顶到栈底为2,1
    < 令c等于stack的栈顶节点 -- 节点2， 此时步骤2的条件（3）命中，将节点2从stack
中弹出并打印，h为节点2，stack从栈顶到栈底为 1
    > 令c等于stack的栈顶节点 -- 节点1， 此时步骤2的条件（2）命中，将节点3压入stack,
h为节点2，stack从栈顶到栈底为3,1
    > 令c等于stack的栈顶节点 -- 节点3， 此时步骤2的条件（1）命中，将节点6压入stack,
h为节点2，stack从栈顶到栈底为6,3,1
    < 令c等于stack的栈顶节点 -- 节点6， 此时步骤2的条件（3）命中，将节点6从stack
中弹出并打印，h为节点6，stack从栈顶到栈底为 3,1
    > 令c等于stack的栈顶节点 -- 节点3， 此时步骤2的条件（2）命中，将节点7压入stack,
h为节点6，stack从栈顶到栈底为7,3,1
    < 令c等于stack的栈顶节点 -- 节点7， 此时步骤2的条件（3）命中，将节点7从stack
中弹出并打印，h为节点7，stack从栈顶到栈底为 3,1
    < 令c等于stack的栈顶节点 -- 节点3， 此时步骤2的条件（3）命中，将节点3从stack
中弹出并打印，h为节点3，stack从栈顶到栈底为 1
    < 令c等于stack的栈顶节点 -- 节点1， 此时步骤2的条件（3）命中，将节点1从stack
中弹出并打印，h为节点1，stack从栈顶到栈底为 null
 */

    public void posOrderUnRecur2(Node h) {
        System.out.println("pos-order: ");
        if (h != null) {
            Node c = null;
            Stack<Node> stack = new Stack<>();
            stack.push(h);
            while (!stack.isEmpty()) {
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }
            }
        }
        System.out.println();
    }

}
