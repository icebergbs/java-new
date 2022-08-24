package com.design.behaviour.strategy.case3;

import java.io.File;

/**
 * @author bingshan
 * @date 2022/8/24 12:45
 */
public class Sorter1 {
    private static final long GB = 1000 * 1000 * 1000;
    public void sortFile(String filePath) {
        // 省略校验逻辑
        File file = new File(filePath);
        long fileSize = file.length();
        ISortAlg sortAlg; if (fileSize < 6 * GB) {
            // [0, 6GB)
            sortAlg = SortAlgFactory1.getSortAlg("QuickSort");
        } else if (fileSize < 10 * GB) {
            // [6GB, 10GB)
            sortAlg = SortAlgFactory1.getSortAlg("ExternalSort");
        } else if (fileSize < 100 * GB) {
            // [10GB, 100GB)
            sortAlg = SortAlgFactory1.getSortAlg("ConcurrentExternalSort");
        } else {
            // [100GB, ~)
            sortAlg = SortAlgFactory1.getSortAlg("MapReduceSort");
        }
        sortAlg.sort(filePath);
    }
}
