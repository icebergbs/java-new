package main.java11;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) 新的本机不可修改集合 APi
 * @author bingshan
 * @date 2022/5/30 12:57
 */
public class CollectionImmutable {

    public static void main(String[] args) {
        System.out.println("--- immutableCollection-----");
        var list = List.of("Java", "Python", "C");
        var copy = List.copyOf(list);
        System.out.println(list == copy); //true

        System.out.println("--- not immutableCollection-----");
        var list2 = new ArrayList<String>();
        var copy2 = List.copyOf(list);
        System.out.println(list2 == copy2); //false

        /**
         * copyOf方法会先判断来源集合是不是 AbstractImmutableList 类型。
         * 如果是， 就直接返回，如果不是， 则调用of创建一个新的集合。
         *
         * 使用of和copyOf创建的集合为不可变集合，不可进行添加、删除、替换、排序等操作。
         * Set和Map接口都有
         */
    }
}
