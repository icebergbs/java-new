package com.design.behaviour.strategy.case3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingshan
 * @date 2022/8/24 12:42
 */

/**
 * 实际上，上面的代码还可以继续优化。
 * 每种排序类都是无状态的，我们没必要在每次使用的时候，都重新创建一个新的对象。
 * 所以，我们可以使用工厂模式对对象的创建进行封装。按照这个思路，我们对代码进行重构。
 */
public class SortAlgFactory1 {
    private static final Map algs = new HashMap<>();
    static {
        algs.put("QuickSort", new QuickSort());
        algs.put("ExternalSort", new ExternalSort());
        algs.put("ConcurrentExternalSort", new ConcurrentExternalSort());
        algs.put("MapReduceSort", new MapReduceSort());
    }
    public static ISortAlg getSortAlg(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("type should not be empty.");
        }
        return (ISortAlg) algs.get(type);
    }
}
