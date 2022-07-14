package com.algorithm.nowcoder.string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CD97_将整数字符串转成整数值 {
    /**
     * 题目描述
     * 给定一个字符串str，如果str符合日常书写的整数形式，并且属于32位整数范围，返回str代表的整数值，否则返回0。
     * 输入描述:
     * 输出包括一行代表str（1 \leq length(str) \leq 100）（1≤length(str)≤100）。
     * 输出描述:
     * 输出一行，代表返回的值。
     * 示例1
     * 输入
     * 123
     * 输出
     * 123
     * <p>
     * 示例2
     * 输入
     * 023
     * 输出
     * 0
     */

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        char[] arr = str.toCharArray();
        System.out.print(getNum(arr));
    }

    public static boolean isValid(char[] arr) {
        if (arr[0] != '-' && (arr[0] < '0' || arr[0] > '9')) {
            return false;
        }
        if (arr[0] == '-' && (arr[0] == '0' || arr.length == 1)) {
            return false;
        }
        if (arr[0] == '0') {
            return false;
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < '0' || arr[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static int getNum(char[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (!isValid(arr)) {
            return 0;
        }
        boolean flag = arr[0] == '-' ? true : false;//负数时候标志位为true
        long res = 0;
        long cur = 0;
        long temp;
        for (int i = flag ? 1 : 0; i < arr.length; i++) {
            //字符值转成数值的过程中不能使用Long。valueOf()方法
            //使用arr[i]-'0'的形式;
            temp = arr[i] - '0';
            res = res * 10L + temp;
            cur = flag ? -res : res;
            if (cur < Integer.MIN_VALUE || cur > Integer.MAX_VALUE) {
                return 0;
            }
        }
        int res1 = (int) res;
        return flag ? -res1 : res1;
    }
}
