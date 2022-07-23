package com.algorithm.codinginterviewguide.chapter1;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目:
 *      给定一个整形矩阵map, 其中的值只有 0和1两种, 求其中全是1的所有矩阵区域中, 最大的矩阵区域为1的数量
 *      例如:
 *          1 1 1 0
 *          其中,最大的矩阵区域有3 个 1, 所以返回3
 *      在如:
 *          1 0 1 1
 *          1 1 1 1
 *          1 1 1 0
 *          其中,最大的矩阵区域有 6 个 1, 所以返回 6
 *
 */
public class C08_3x_求最大子矩阵的大小 {


    public static int getMaxSize(int[][] map) {
        int size = 0;
        if (map.length == 0) {
            return size;
        }
        int [] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j ++) {
                if (map[i][j] == 1) {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }
           int maxArea =  getNearLessRepeat(height, map);
            if (maxArea > size) {
                size= maxArea;
            }
        }
        return size;
    }

    public static int getNearLessRepeat(int[] height, int[][] map) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                Integer j = stack.pop();
                //取位于下面位置
                int k = stack.isEmpty() ? -1 : stack.peek();
                int maxAera = (i - k - 1) * height[j];
                if ( maxAera > max) {
                    max = maxAera;
                }
            }
            if (height[stack.peek()] < height[i]) {
                stack.push(i);
            }
        }
        int i = height.length;
        while (!stack.isEmpty()) {
            Integer j = stack.pop();
            //取位于下面位置的列表中, 最晚加入的那个
            int k = stack.isEmpty() ? -1 : stack.peek();
            int maxAera = (i - k - 1) * height[j];
            if ( maxAera > max) {
                max = maxAera;
            }
        }
        return max;
    }


    /**
     * 解答:
     *      如果矩阵大小为 O(N*M), 可以做到时间复杂度为O(N*M)
     *
     *      1. 矩阵的行数为N, 可以以每行做切割, 统计以当前行作为底的情况下, 每个位置往上的1 的数量. 使用高度数组height表示.
     *          例如:
     *              map = 1 0 1 1
     *                    1 1 1 1
     *                    1 1 1 0
     *              以第1行做切割后, height={1, 0, 1, 1}, height[j]表示在目前的底(第1行)的j位置往上(包括j位置), 有多少个连续的1.
     *              以第2行做切割后, height={2, 1, 2, 2}
     *              以第3行做切割后, height={3, 2, 3, 0}
     *      2. 对于每一次切割,都利用更新后的height数组来求出以当前行为底的情况下, 最大的矩阵是什么.
     *
     *      步骤2的实现:
     *          height数组的长度为M, 那么求解过程可以做到时间复杂度为O(M)
     *
     *          对于height数组,可以理解为一个直方图, 比如{3,2,3,0}
     *               3     3
     *              __  2 __
     *              | |__| |
     *              | |  | |
     *              | |  | | 0
     *              ----------
     *           步骤2的实质是在一个大的直方图中求最大矩形的面积. 如果我们能够求出以每根柱子扩展出去的最大矩形,那么其中最大的矩形就是我们想找的.
     *              1) 第一根高度为3的柱子无法向左扩展,他的右边是2, 所以向右也无法扩展, 则面积 3 * 1 = 3
     *              2) 第二根高度为2的柱子向左可以扩展一个距离,因为他的左边是3,比2大; 右边也是3,所以向右也可以扩1个距离, 则矩形面积 2*3 = 6
     *              3) 第三根高度为3的柱子向没法扩展,向右也没法扩展, 则矩形面积 3 * 1 = 3
     *              4) 第四根高度为0的柱子向左向右无法扩展, 则矩形面积 0* 1 = 0
     *           考察每根柱子最大能扩展多大,这个行为的实质就是找到柱子左边离它最近且比它小的柱子位置在哪里,以及右边离它最近且比它小的柱子位置在那里.
     *           这个过程怎么计算最快?
     *                  利用单调栈
     *
     *     为了方便表述,我们以height={3,4,5,4,3,6}为例说明如何求数组中最大矩阵
     *         1.height[3] < height[2] ,则把栈中存的位置不断弹出,直到某一个栈顶所代表的值小于height[i],在把位置i压入,并在这期间做如下处理:
     *              1)假设当前弹出的栈顶位置记为位置j, 弹出栈顶之后,新的栈顶记为k. 然后开始考虑位置j的柱子向右和向左最远能扩到那里.
     *              2)对位置j的柱子来说,向右最远能扩到那里呢?
     *                  如果height[j]>height[i], 那么i-1位置就是向右能扩展的最远位置.
     *                  如果height[j]==height[i], 那么i-1位置不一定是向右能扩到的最远位置,只是起码能扩展到的位置,那怎么办呢?
     *                  可以肯定的是,在这种情况下, i位置的柱子向左必然也可扩到j位置.j和i扩的最大矩形是同一个.
     *                  所以, j位置能扩的最大矩形位置虽然无法被正确计算,但不要紧,因为i位置肯定要压入到栈中,那么j位置和i位置共享的最大矩形
     *                      就等i位置弹出的时候再计算即可.
     *              3)对于j位置, 向左能最远扩到那里?
     *                  根据单调栈的性质,k位置的值是j位置的值左边离j位置最近的比j位置的最小的位置, 所以j位置的柱子向左最远可以扩到 k+1
     *              4)宗上所述,j位置能扩的最大矩形为 (i-k-1)*height[j]  // i-1 - (k+1) + 1
     *
     *         2. 遍历结束, stack中仍有位置没有经历扩的过程. 此时因为height数组再往右不能扩出去, 所以 i==height.length==6且越界之后的值极小,
     *            然后开始弹出留在栈中的位置.
     *
     * @param map
     */
    public int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea =Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i-k-1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length -k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }



    public static void main(String[] args) {

    }


}
