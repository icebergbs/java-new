package com.design.behaviour.state.gateway;

/**
 * 状态接口
 */
public interface IGateway {

    /**
     * 停用
     */
    void stopEvent(GatewayStateMachine stateMachine);

    /**
     * 作废
     */
    void cannelEvent(GatewayStateMachine stateMachine);

    /**
     * 启用
     */
    void startEvent(GatewayStateMachine stateMachine);

}
