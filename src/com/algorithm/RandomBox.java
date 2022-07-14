package com.algorithm;

public class RandomBox {
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
