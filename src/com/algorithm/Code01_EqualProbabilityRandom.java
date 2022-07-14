package com.algorithm;


public class Code01_EqualProbabilityRandom {

    public static int random(RandomBox randomBox, int from, int to) {
        if (from == to) {
            return from;
        }
        int range = to - from;
        int num = 1;
        while ((1 << num) - 1 < range) {
            num++;
        }
        int ans = 0;
        do {
            for (int i = 0; i < num; i++) {
                ans += rand01(randomBox) << i;
            }
        } while (ans > range);
        return ans + from;

    }

    public static int rand01(RandomBox randomBox) {
        int min = randomBox.min;
        int max = randomBox.max;
        int size = max - min + 1;
        boolean odd = (size & 1) != 0;
        int mid = size / 2;
        int ans = 0;
        do {
            ans = randomBox.random() - min;
        } while (odd && ans == mid);

        return ans < mid ? 0 : 1;
    }


    public static class RandomBox {
        private final int min;
        private final int max;

        public RandomBox(int mi, int ma) {
            min = mi;
            max = ma;
        }

        public int random() {
            return min + (int) (Math.random() * (max - min + 1));
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

    }
}
