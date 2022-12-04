package com.design.principle.c16;

/**
 * @author bingshan
 * @date 2022/12/4 11:39
 */
public class Demo {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略设置apiStatInfo数据值的代码
        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}
