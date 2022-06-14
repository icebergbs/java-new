package main.java9.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bingshan
 * @date 2022/5/19 13:01
 */
public class TestOptional {
    public static void main(String[] args) {
        /**
         * stream() 方法
         */
        System.out.println("-------stream---------");
        //stream 方法的作用就是将 Optional 转为一个 Stream，如果该 Optional 中包含值，
        //那么就返回包含这个值的 Stream，否则返回一个空的 Stream（Stream.empty()）。
        List<Optional<String>> list = Arrays.asList(
                                    Optional.empty(),
                                    Optional.of("A"),
                                    Optional.empty(),
                                    Optional.of("B"));

        //if optional is non-empty, get the value in stream, otherwise return empty
        List<String> filteredList = list.stream()
                .flatMap(o ->  o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList());

        System.out.println(filteredList);

        //Optional::stream method will return a stream of either one
        //or zero element if data is present or not.
        List<String> filteredListJava9 = list.stream().flatMap(Optional::stream)
                .collect(Collectors.toList());
        System.out.println(filteredListJava9);

        /**
         * ifPresentOrElse() 方法
         */
        System.out.println("-------ifPresentOrElse---------");
        //ifPresentOrElse 方法的改进就是有了 else，接受两个参数 Consumer 和 Runnable。

        //ifPresentOrElse 方法的用途是，如果一个 Optional 包含值，则对其包含的值调用函数 action，
        //即 action.accept(value)，这与 ifPresent 一致；
        //
        //与 ifPresent 方法的区别在于，ifPresentOrElse 还有第二个参数 emptyAction —— 如果 Optional 不包含值，
        //那么 ifPresentOrElse 便会调用 emptyAction，即 emptyAction.run()。
        Optional<Integer> optional = Optional.of(1);
        optional.ifPresentOrElse(x -> System.out.println("value: " + x),
                () -> System.out.println("Not present."));
        Optional optional1 = Optional.empty();
        optional1.ifPresentOrElse(x -> System.out.println("value:" + x),
                () -> System.out.println("Not present."));

        /**
         * or() 方法
         */
        System.out.println("-------or()---------");
        Optional<String> optional11 = Optional.of("Mahesh");
        Supplier<Optional<String>> supplierString = () -> Optional.of("Not Present");
        optional11 = optional11.or(supplierString);
        optional11.ifPresent( x -> System.out.println("Value: " + x));
        optional11 = Optional.empty();
        optional11 = optional11.or( supplierString);
        optional11.ifPresent( x -> System.out.println("Value: " + x));


    }
}
