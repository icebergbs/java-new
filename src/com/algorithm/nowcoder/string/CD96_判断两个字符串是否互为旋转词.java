package com.algorithm.nowcoder.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CD96_判断两个字符串是否互为旋转词 {
    //	题目描述
    //	如果一个字符串为str，把字符串的前面任意部分挪到后面形成的字符串交str的旋转词。比如str=“12345”，str的旋转串有“12345”、“45123”等等。给定两个字符串，判断是否为旋转词。
    //	输入描述:
    //		输出包含三行，第一个两个整数n和m（1 \leq n,m \leq10^5 ）（1≤n,m≤105），分别表示两个字符串的长度。第二行和第三行各输入一个字符串。
    //	输出描述:
    //		如果两个字符串互为旋转词请输出“YES”，否则输出“NO”。

    //	示例1
    //		输入 
    //			4 4
    //			abcd
    //			cdab
    //		输出 
    //			YES

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strings = br.readLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        String str1 = br.readLine();
        String str2 = br.readLine();
        if (n != m) {
            System.out.println("NO");
            return;
        }
        System.out.println(isDeformation(str1, str2) ? "YES" : "NO");
    }

    public static boolean isDeformation(String str1, String str2) {
        String string = str1 + str1;
        return (string.indexOf(str2) != -1);
    }

}
