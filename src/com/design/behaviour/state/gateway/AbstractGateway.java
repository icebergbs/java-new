package com.design.behaviour.state.gateway;

public class AbstractGateway implements IGateway {

    protected static final RuntimeException EXCEPTION = new RuntimeException("操作不允许!!!");


    @Override
    public void stopEvent(GatewayStateMachine stateMachine) {
        throw EXCEPTION;
    }

    @Override
    public void cannelEvent(GatewayStateMachine stateMachine) {
        throw EXCEPTION;
    }

    @Override
    public void startEvent(GatewayStateMachine stateMachine) {
        throw EXCEPTION;
    }
}
