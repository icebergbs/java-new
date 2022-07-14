package com.design.behaviour.state.gateway;

public class ApplicationDemo {
    public static void main(String[] args) {
        int state = 1;
        String action = "停用";
        GatewayStateMachine stateMachine = null;
        if (state == 1) {
            stateMachine = new GatewayStateMachine(StartGateway.getInstance());
        }
        if (state == 2) {
            stateMachine = new GatewayStateMachine(StopGateway.getInstance());
        }
        if (state == 3) {
            stateMachine = new GatewayStateMachine(CannelGateway.getInstance());
        }
        if ("停用".equals(action)) {
            stateMachine.getCurrentState().stopEvent(stateMachine);
        }
        if ("作废".equals(action)) {
            stateMachine.getCurrentState().cannelEvent(stateMachine);
        }
        if ("启用".equals(action)) {
            stateMachine.getCurrentState().startEvent(stateMachine);
        }
    }
}
