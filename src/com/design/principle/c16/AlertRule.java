package com.design.principle.c16;
/**
 *   存储告警规则，可以自由设置。
 *
 * @author  bingshan
 * @date 2022/12/4 11:03
 */
public class AlertRule {


    public Rule getMatchedRule(String api) {
        return  new Rule();
    }
}
