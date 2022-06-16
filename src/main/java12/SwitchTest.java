package main.java12;

/**
 * @author bingshan
 * @date 2022/6/15 12:44
 */
public class SwitchTest {

    public static void main(String[] args) {
        //预览功能，如果该 jdk12 的 switch 效果不好的话，会被下一版本 jdk13 直接移除该功能，并不是之后每个版本必须的。

        //1. 许多 break 使它不必要地冗长，如果忘记写 break，则会产生意料之外的结果或者异常，所以针对于此 jdk12 在这里进行了优化升级。
        //JDK12: 我们建议引入一种新形式的开关标签，写成 “case L ->” 表示如果标签匹配，则只执行标签右侧的代码。
        // JDK13 不支持
//        Day day = Day.MONDAY;
//        switch (day) {
//            case MONDAY, FRIDAY, SUNDAY -> System.out.println(6);
//            case TUESDAY                -> System.out.println(7);
//            case THURSDAY, SATURDAY     -> System.out.println(8);
//            case WEDNESDAY              -> System.out.println(9);
//        }

        //2.  JDK12: 将此表达为一种陈述是迂回的，重复的，并且容易出错。意味着我们应该计算 numLetters 每一天的价值。应该可以直接说，使用更清晰，更安全 Switch 表达式
        //JDK13 不支持
//        Day day = Day.MONDAY;
//        int numLetters = switch (day) {
//            case MONDAY, FRIDAY, SUNDAY -> 6;
//            case TUESDAY                -> 7;
//            case THURSDAY, SATURDAY     -> 8;
//            case WEDNESDAY              -> 9;
//        };
//        System.out.println("day= " + day);
//        System.out.println("numLetters= " + numLetters);





    }

}
