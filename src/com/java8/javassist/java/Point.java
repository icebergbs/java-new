package com.java8.javassist.java;

/**
 *
 *
 * @author  bingshan
 * @date 2022/3/16 20:45
 */
@Author(name = "bingshan", year=2022)
public class Point {
    int x, y;
    void move(int dx, int dy) { x += dx; y += dy; }

    void move1(int dx, int dy) { x += dx; y += dy; }

    int fact(int n) {
        if (n <= 1)
            return n;
        else
            return n * fact(n - 1);
    }
}
