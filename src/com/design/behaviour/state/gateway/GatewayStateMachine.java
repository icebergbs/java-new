package com.design.behaviour.state.gateway;

public class GatewayStateMachine {
    private IGateway currentState;

    public GatewayStateMachine(IGateway currentState) {
        this.currentState = currentState;
    }

    public IGateway getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IGateway currentState) {
        this.currentState = currentState;
    }
}
