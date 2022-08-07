package com.algorithm.codinginterviewguide.chapter1;


import java.util.LinkedList;
import java.util.Stack;

/**
 * 题目:
 *    给定数组arr 和 整数num, 共返回有多少个子数组满足如下情况:
 *      max(arr[i..j]) - min(arr[i..j]) <= num
 *      max(arr[i..j]) 表示子数组arr[i..j] 中的最大值, min(arr[i..j]) 表示子数组arr[i..j]中的最小值
 *
 *  要求:
 *      如果数组长度为N, 请实现时间复杂度为O(N) 的解法
 */
public class C09_3x_最大值减去最小值小于或等于num的子数组数量 {


    /**
     * 解法:
     *      生成两个双端队列 qmax 和 qmin, 当子数组为 arr[i..j]时, qmax维护了窗口子数组arr[i..j]的最大值更新的结构, qmin维护了窗口子数组
     *      arr[i..j]的最小值更新的结构. 当子数组arr[i..j]向右扩一个位置变成arr[i..j+1]时, qmax和qmin结构更新代价的平均时间复杂度为O(1),
     *      并且可以在O(1)的时间内得到arr[i..j+1]的最大值和最小值. 当子数组arr[i..j]向左缩一个位置变成arr[i+1 .. j]时, qmax 和 qmin
     *      结构更新代价的平均时间复杂度为O(1), 并且在O(1)的时间内得到arr[i+1 .. j]的最大值和最小值.
     *
     *      通过分析题目满足的条件, 可以得到如下两个结论:
     *          1. 如果子数组arr[i..j]满足条件, 即max(arr[i..j]) - min(arr[i..j]) <=num, 那么arr[i..j]中的每一个子数组,
     *              即arr[k..l](i<=k<=l<=j)都满足条件. 我们以子数组arr[i..j-1]为例说明, arr[i..j-1]最大值只可能小于或等于arr[i..j]
     *              的最大值, arr[i..j-1]最小值只可能大于或等于arr[i..j]的最小值, 所以arr[i..j-1]必然满足条件. 同理, arr[i..j]中的
     *              每一个子数组都满足条件.
     *          2. 如果子数组arr[i..j]不满足条件, 那么所有包含arr[i..j]的子数组, 即arr[k..l](k<= i <= j <=l)都不满足条件.
     *              证明过程同第一个结论.
     *
     *
     *       设计整个过程如下:
     *          1. 生成两个双端队列qmax 和 qmin, 生成两个整形变量i 和 j, 表示子数组的范围, 即arr[i..j]. 生成整形变量res, 表示所有满足条件
     *              的子数组数量.
     *          2. 令 j 不断向右移动(j++), 表示arr[i..j]一直向右扩大, 并不断更新qmax和qmin结构, 保证qmax和qmin始终维持动态窗口最大值和最小值的更新结构.
     *              一旦出现arr[i..j]不满足条件的情况,j向右扩的过程停止, 此时arr[i..j-1] arr[i..j-2] arr[i..j-3] ... arr[i..i]
     *              一定都是满足条件的. 也就是说, 所有必须以arr[i]作为第一个元素的子数组,满足条件的数量为 j-i个. 于是令 res += j-i
     *          3. 当进行完步骤2, 令i向右移动一个位置, 并对qmax和qmin做出相应的更新, qmax和qmin从原来的arr[i..j]窗口的最大值和最小值的
     *              更新结构.然后重复步骤2, 也就是求所有必须以arr[i+1]作为第一个元素的子数组中, 满足条件的数量有多少个.
     *          4. 根据步骤2 和步骤3, 依次求出: 必须以arr[0]开头的子数组, 满足条件的数量有多少个; 必须以arr[i+1]开头的子数组, 满足条件的数量有多少个;
     *              ....; 全部累加起来就是答案.
     *
     *          上述过程, 所有的下标值最多进 qmax 和 qmin一次, 出qmax 和qmin一次. i 和 j 的值也不断增加,并且从来不减小. 所以整个过程的时间复杂度为O(N)
     *
     * @param arr
     */

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }

        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != j) {
                    while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                        qmin.pollLast();
                    }
                    qmin.addLast(j);
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                        qmax.pollLast();
                    }
                    qmax.addLast(j);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            res += j - i;
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            i++;
        }
        return res;
    }


    public static void main(String[] args) {

    }


}
