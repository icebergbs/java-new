package main.java9.trywithresources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * @author bingshan
 * @date 2022/5/19 12:47
 */
public class TestTws {
    public static void main(String[] args) throws IOException {
        System.out.println(readData("test"));
    }

    static String readData(String message) throws IOException {
        Reader inputString = new StringReader(message);
        BufferedReader br = new BufferedReader(inputString);
        //不需要声明资源 br1 就可以使用它
        try (br){
            return br.readLine();
        }
    }
}
