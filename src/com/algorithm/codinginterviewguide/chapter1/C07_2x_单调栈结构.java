package com.algorithm.codinginterviewguide.chapter1;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 题目:
 *      给定一个不含重复值的数组arr, 找到每一个i位置左边和右边离i位置最近且比 arr[i]小的位置.
 *      返回所有位置相应的信息.
 *
 *      举例:
 *          arr = {3, 4, 1, 5, 6, 2, 7}
 *          返回如下二维数组作为结果:
 *              {
 *                  {-1, 2},
 *                  {0, 2},
 *                  {-1, -1},
 *                  {2, 5},
 *                  {3, 5},
 *                  {2, -1},
 *                  {5, -1}
 *              }
 *           -1表示不存在.
 *
 *       要求:  时间复杂度为 O(N)

 *
 */
public class C07_2x_单调栈结构 {

    private static int[] arr = {3, 4, 1, 5, 6, 2, 7};


    public static int[][] getNearPotionxx(int[] arr, int len) {
        int[][] res = new int[len][2];
        Stack<Integer> desStack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (desStack.isEmpty()) {
                desStack.push(i);
            } else {
                if (arr[desStack.peek()] < arr[i]) {
                    desStack.push(i);
                } else {
                    while (!desStack.isEmpty() && arr[desStack.peek()] >= arr[i]) {
                        int index = desStack.peek();
                        res[index][1] = i;
                        desStack.pop();
                        if (desStack.isEmpty()) {
                            res[index][0] = -1;
                        } else {
                            res[index][0] = desStack.peek();
                        }
                    }
                    desStack.push(i);
                }

            }
        }
        while (!desStack.isEmpty()) {
            int index = desStack.peek();
            res[index][1] = -1;
            desStack.pop();
            if (desStack.isEmpty()) {
                res[index][0] = -1;
            } else {
                res[index][0] = desStack.peek();
            }
        }

        return res;

    }

    /**
     * 单调栈:
     *      原问题: 准备一个栈, 记为stack<Integer>, 栈中放的元素是数组的位置, 开始时stack为空.
     *              如果找到每一个 i位置左边和右边离i 位置最近且值比 arr[i]小的位置,
     *                那么需要让stack从栈顶到栈底的位置所代表的值是严格递减的;
     *              如果找到每一个 i位置左边和右边离i 位置最近且值比 arr[i]大的位置,
     *                那么需要让stack从栈顶到栈底的位置所代表的值是严格递增的;
     */

    /**
     * 解答:
     *      关键在于生成所有位置的相应信息, 时间复杂度做到 O(N), 这需要用到单调栈结构.
     *
     *      遍历到arr[0]=3, 发现stack为空,就直接放入0位置.
     *      遍历到arr[1]=4, 发现直接放入1位置,不会破坏stack从栈顶到栈底的位置所代表的值是严格递减的,那么直接放入.
     *      遍历到arr[2]=1,直接放入会破坏stack严格递减,所以从stack开始
     *          循环弹出位置,直到不会破坏stack严格递减:
     *              1)如果x位置被弹出,在栈中位于x位置下面的位置,就是x位置左边离x位置最近且值比arr[x]小的位置.
     *              2)当前遍历到的位置就是x位置右边离x位置最近且值比arr[x]小的位置
     *      ....
     *      遍历阶段结束后,清算栈中剩下的位置.
     *          弹出6位置,栈中它的下面是位置5, 6位置是清算阶段弹出的, 所以ans[6]={5, -1};
     *
     * 证明:
     *                 当前 j : 4
     *      | x=5    |
     *      | i=1    |
     *      ---------   stack
     *
     *      如果x位置被弹出, 在栈中位于x位置下面的位置为什么就是x位置左边离x位置最近且值比arr[x]小的位置;
     *          当前j位置, x位于栈中,所以x在j的左边. 如果5 和 4之间存在小于5的数,那么没等遍历到当前的4, x(5)位置就已经被弹出.
     *          所以5和4之间的数要么没有,要么一定比5大.
     *
     *      当前遍历到的位置就是x位置右边离x位置最近且值比arr[x]小的位置;
     *          当前弹出的是x位置,x位置下面是位置i, i比x早进栈,所以i位置肯定在x位置的左边. 如果1和5之间存在小于1的数, 那么i(1)位置是会被提前弹出.
     *          如果在1和5之间存在大于1但小于5的数,那么栈中i位置和x位置之间一定会加上一个别的位置.也不可能贴在一起.
     *          所以1和5之间的数要么没有,要么一定比5大.
     *
     *  整个流程中, 每个位置都进栈一次\ 出栈一次, 所以整个流程的时间复杂度就是 O(N)
     *
     * @param arr
     * @return
     */

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];

        /**
         *  FUNCTION-******
         *  单调栈
         */
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    /**
     * 进阶问题:
     *      给定一个可能含有重复值的数组arr 找到每一个i位置左边和右边离i位置最近且比 arr[i]小的位置.
     *      返回所有位置相应的信息.
     *
     * @param arr
     */
    public static int[][] getNearLessRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> popIs = stack.pop();
                //取位于下面位置的列表中, 最晚加入的那个
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() -1);
                for (Integer popi : popIs) {
                    res[popi][0] = leftLessIndex;
                    res[popi][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(Integer.valueOf(i));
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            //取位于下面位置的列表中, 最晚加入的那个
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() -1);
            for (Integer popi : popIs) {
                res[popi][0] = leftLessIndex;
                res[popi][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        getNearPotionxx(arr, arr.length);
    }

}
