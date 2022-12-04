package com.design.principle.c16;

import java.util.ArrayList;
import java.util.List;

/**
 *  责任链模式的变体，所有节点遍历执行。 经典实现是只在某个节点执行并return；
 *
 * @author  bingshan
 * @date 2022/12/4 10:58
 */
// 代码未改动...
public class Alert1 {
    public static List<AlertHandler1> alertHandlers = new ArrayList<>();

    public static void addAlertHandler(AlertHandler1 alertHandler) {
        alertHandlers.add(alertHandler);
        return;
    }

    public void check(ApiStatInfo1 apiStatInfo1) {
        for (AlertHandler1 handler : alertHandlers) {
            handler.check(apiStatInfo1);
        }
    }
}
