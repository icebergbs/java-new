package com.design.behaviour.strategy.case3;

import java.io.File;

/**
 * @author bingshan
 * @date 2022/8/24 12:38
 */

/**
 * 假设有这样一个需求，希望写一个小程序，实现对一个文件进行排序的功能。
 * 文件中只包含整型数，并且，相邻的数字通过逗号来区隔。
 *
 *
 * 如果文件更大，比如有 100GB 大小，我们为了利用 CPU 多核的优势，可以在外部排序的基础之上进行优化，加入多线程并发排序的功能，这就有点类似“单机版”的 MapReduce。
 * 如果文件非常大，比如有 1TB 大小，即便是单机多线程排序，这也算很慢了。这个时候，我们可以使用真正的 MapReduce 框架，利用多机的处理能力，提高排序的效率。
 */
public class Sorter {

    private static final long GB = 1000 * 1000 * 1000;
    public void sortFile(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg;
        if (fileSize < 6 * GB) {
            // [0, 6GB)
            sortAlg = new QuickSort(); }
        else if (fileSize < 10 * GB) {
            // [6GB, 10GB)
            sortAlg = new ExternalSort(); }
        else if (fileSize < 100 * GB) {
            // [10GB, 100GB)
            sortAlg = new ConcurrentExternalSort(); }
        else {
            // [100GB, ~)
            sortAlg = new MapReduceSort();
        }
        sortAlg.sort(filePath);
    }
}
