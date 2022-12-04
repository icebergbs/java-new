package com.design.principle.c16;

/**
 * @author bingshan
 * @date 2022/12/4 11:52
 */
public class Demo1 {
    public static void main(String[] args) {
        ApiStatInfo1 apiStatInfo1 = new ApiStatInfo1();
        // ...省略apiStatInfo的set字段代码
        apiStatInfo1.setTimeoutCount(289); // 改动四：设置tiemoutCount值
        ApplicationContext1.getInstance().getAlert().check(apiStatInfo1);
    }
}
