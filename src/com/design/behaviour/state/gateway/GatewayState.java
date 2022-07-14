package com.design.behaviour.state.gateway;

/**
 * 状态枚举
 */
public enum GatewayState {
    START(1, "启用"),
    STOP(2, "停用"),
    CANCEL(3, "作废");


    /**
     * 状态值
     */
    private Integer key;
    /**
     * 状态名称
     */
    private String value;


    GatewayState(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
