package com.algorithm.codinginterviewguide.chapter1;


import java.util.LinkedList;

/**
 * 题目:
 *   有一个整形数组 arr 和一个大小为 w的窗口从数组的最左边滑倒最右边,
 *   窗口每次向右边滑一个位置
 *   例如,数组为[4.3.5.4.3.3.6.7], 窗口大小为3时:
 *      [4 3 5] 4 3 3 6 7 窗口中最大值为5
 *      ...
 *
 *      如果数组长度为n, 窗口大小为w, 则一共产生 n-w+1 个窗口的最大值
 *
 *   请实现一个函数.
 *      1. 输入: 整形数组arr, 窗口大小为 w
 *      2. 输出: 一个长度为 n-w+1 的数组 res, res[i]表示每一种窗口状态下的最大值
 *      以本题为例: 结果应该返回{5,5,5,4,6,7}
 *
 */
public class C06_2x_生成窗口最大值数组 {

    /**
     * 解答:
     *     假设数组长度为N, 窗口大小为w
     *     时间复杂度为O(N)的实现
     *
     *     本题的关键在于利用双端队列来实现窗口最大值的更新. 首先生成双端队列qmax.
     *     假设遍历到arr[i], qmax的放入规则为:
     *      1. 如果qmax为空,直接把下标 i 放进 qmax, 放入过程结束.
     *      2. 如果qmax不为空, 取出当前qmax队尾存放的下标.假设为j.
     *          1) 如果arr[j] > arr[i], 直接把下标 i 放进qmax的队尾
     *          2) 如果arr[j] <= arr[i], 把j 从qmax中弹出,重复qmax的放入规则
     *      遍历到arr[i], qmax的弹出规则:
     *          如果qmax对头的下标等于 i-w, 说明当前qmax对头的下标已过期,弹出当前对头的下标即可
     *
     *
     *      每个下标值最多进qmax一次,出qmax一次,所以操作时间复杂度为O(N)
     * @param arr
     * @param w
     * @return
     */
    public int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() || arr[qmax.getLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.addLast(i);
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w -1) {
                res[index ++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }


}
