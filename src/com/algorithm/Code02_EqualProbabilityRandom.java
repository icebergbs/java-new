package com.algorithm;

public class Code02_EqualProbabilityRandom {
    public static void main(String[] args) {
        //����һ�����飬�����һ�������ִ�������һ�룬�����������
        //���û���κ�һ�������ִ�������һ�룬���� -1
        //Ҫ��ʱ�临�Ӷ�O(N), ����ռ临�Ӷ�O(1)
        int[] arr = {1, 4, 4, 4, 5};
        int a = halfMajor(arr);
        System.out.println(a);
    }

    public static int halfMajor(int[] arr) {
        int cand = 0;
        int HP = 0;
        for (int i = 0; i != arr.length; i++) {
            if (HP == 0) {
                cand = arr[i];
                HP = 1;
            } else if (cand == arr[i]) {
                HP++;
            } else {
                HP--;
            }
        }
        if (HP == 0) {
            return -1;
        }
        HP = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] == cand) {
                HP++;
            }
        }

        return HP > arr.length / 2 ? cand : -1;

    }
}
