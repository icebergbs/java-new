package com.algorithm.codinginterviewguide.chapter1;


import java.util.LinkedList;

/**
 * 题目:

 *
 */
public class C06_2x_生成窗口最大值数组 {

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
