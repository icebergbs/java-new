package main.java9.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author bingshan
 * @date 2022/5/16 12:35
 */
public class TestStream {
    public static void main(String[] args) {

        //takeWhile 方法
        //takeWhile() 方法使用一个断言作为参数，返回给定 Stream 的子集直到断言语句第一次返回 false。
        //如果第一个值不满足断言条件，将返回一个空的 Stream。
        System.out.println("-----takeWhile-----");
        Stream.of("a", "b", "c", "", "d", "e", "f")
                .takeWhile(item -> !item.isEmpty())
                .forEach(System.out::println);

        //dropWhile 方法
        //dropWhile 方法和 takeWhile 作用相反的，使用一个断言作为参数，直到断言语句第一次返回 false 才返回给定 Stream 的子集。
        System.out.println("-----dropWhile-----");
        Stream.of("a", "b", "c", "", "d", "e", "f")
                .dropWhile(item -> !item.isEmpty())
                .forEach(System.out::println);

        //iterate 方法
        //方法允许使用初始种子值创建顺序（可能是无限）流，并迭代应用指定的下一个方法。
        //当指定的 hasNext 的 predicate 返回 false 时，迭代停止。
        System.out.println("-----iterate-----");
        IntStream.iterate(3, x -> x < 10, x -> x + 3)
                .forEach(System.out::println);

        //ofNullable 方法
        System.out.println("-----ofNullable-----");
        long count = Stream.ofNullable(100).count();
        System.out.println("count: " + count);
        count = Stream.ofNullable(null).count();
        System.out.println("null count: " + count);
    }
}
