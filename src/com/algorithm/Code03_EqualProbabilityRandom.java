package com.algorithm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Code03_EqualProbabilityRandom {
    //����һ������ΪN������arr, ��һ������1������K
    //�������Щ�����ִ�������N/k  �ͷ�����Щ��
    //ʱ��O(N)  �ռ�O(K)
    public static List<Integer> kMajor(int[] arr, int k) {
        List<Integer> ans = new ArrayList<>();
        if (k < 2) {
            return ans;
        }
        HashMap<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.size() == k - 1) {
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }

        HashMap<Integer, Integer> reals = getReals(arr, cands);
        for (Entry<Integer, Integer> set : cands.entrySet()) {
            Integer key = set.getKey();
            if (reals.get(key) > arr.length / k) {
                ans.add(key);
            }
        }
        return ans;
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
        List<Integer> removeList = new LinkedList<>();
        for (Entry<Integer, Integer> set : map.entrySet()) {
            Integer key = set.getKey();
            Integer value = set.getValue();
            if (value == 1) {
                removeList.add(key);
            }
            map.put(key, value - 1);
        }

        for (Integer removeKey : removeList) {
            map.remove(removeKey);
        }
    }

    public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
        HashMap<Integer, Integer> reals = new HashMap<>();
        for (int i = 0; i != arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                if (reals.containsKey(curNum)) {
                    reals.put(curNum, reals.get(curNum) + 1);
                } else {
                    reals.put(curNum, 1);
                }
            }
        }
        return reals;
    }

    public static int[] genareteRandomArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max) + 1;
        }
        return ans;

    }

    public static void main(String[] args) {
        int len = 100;
        int max = 10;
        int testTime = 100000;
        System.out.println("test. begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = genareteRandomArray(len, max);
            List<Integer> ans1 = kMajor(arr, 5);
            System.out.println(ans1.toString());
        }
    }
}
