package com.algorithm.codinginterviewguide.chapter1;

import java.util.Stack;

/**
 * 题目:
 *      汉诺塔问题比较经典, 现修改以下游戏规则:
 *          现在限制不能从最左侧的塔直接移动到最右侧, 也不能从最右侧直接移动到最左侧,
 *          而是必须经过中间.
 *          求当塔有N 层的时候,打印最优移动过程和最优移动总布数.
 *
 */
public class C05_3x_用栈来求解汉诺塔问题 {

    /**
     * 方法一: 递归的方法
     */
    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        /**
         * 1. 如果N层塔都在 "左" , 希望全部移到 "中", 则有三个步骤:
         *      . 将 1 ~ N-1从 "左"移到 "右", 递归实现
         *      . 将第 N 层从 "左" 移到 "中"
         *      . 在将 1 ~ N-1 层从 "右" 移到"左", 递归实现
         *   "中" 移到 "左"
         *   "中" 移到 "右"
         *   "右" 移到 "中"
         */
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            /**
             * 剩下的N层 从 "左" 移到 "右", 则有五个步骤:
             *      . 将 1 ~ N-1 从 "左" 移到 "右", 递归实现
             *      . 将 N层 从 "左" 移到 "中"
             *      . 将 1 ~ N-1 从 "右" 移到 "左" , 递归实现
             *      . 将 N层 从 "中" 移到 "右"
             *      . 将 1 ~ N-1 从 "左" 移到 "右", 递归实现
             */
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /**
     * 方法二: 非递归方法 - 用栈模拟整个过程
     */
    enum Action {
        NO, LToM, MToL, MToR, RToM
    }

    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<>();
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            lS.push(i);
        }
        Action[] record = { Action.NO};
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackTotStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    public static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack,
                                     Stack<Integer> tStack, String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }


}
