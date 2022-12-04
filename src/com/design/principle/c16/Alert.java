package com.design.principle.c16;

import java.util.ArrayList;
import java.util.List;

/**
 *  责任链模式的变体，所有节点遍历执行。 经典实现是只在某个节点执行并return；
 *
 * @author  bingshan
 * @date 2022/12/4 10:58
 */
public class Alert {
    public static List<AlertHandler> alertHandlers = new ArrayList<>();

    public static void addAlertHandler(AlertHandler alertHandler) {
        alertHandlers.add(alertHandler);
        return;
    }

    public void check(ApiStatInfo apiStatInfo) {
        for (AlertHandler handler : alertHandlers) {
            handler.check(apiStatInfo);
        }
    }
}
