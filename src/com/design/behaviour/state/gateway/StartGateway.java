package com.design.behaviour.state.gateway;

public class StartGateway extends AbstractGateway {

    private static final StartGateway instance = new StartGateway();

    private StartGateway() {
    }

    ;

    public static StartGateway getInstance() {
        return instance;
    }

    public GatewayState getState() {
        return GatewayState.START;
    }

    @Override
    public void stopEvent(GatewayStateMachine stateMachine) {
        //TODO  数据库state  1 -> 2
        stateMachine.setCurrentState(StartGateway.getInstance());
        System.out.println("数据库state  1 -> 2");
    }
}
