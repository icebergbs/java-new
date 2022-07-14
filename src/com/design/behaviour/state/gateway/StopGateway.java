package com.design.behaviour.state.gateway;

public class StopGateway extends AbstractGateway {

    private static final StopGateway instance = new StopGateway();

    private StopGateway() {
    }

    ;

    public static StopGateway getInstance() {
        return instance;
    }

    public GatewayState getState() {
        return GatewayState.STOP;
    }

    @Override
    public void startEvent(GatewayStateMachine stateMachine) {
        //TODO 数据库state 2 -> 1
        stateMachine.setCurrentState(StartGateway.getInstance());
        System.out.println("数据库state 2 -> 1");
    }

    @Override
    public void cannelEvent(GatewayStateMachine stateMachine) {
        //TODO  数据库state  2 -> 3
        stateMachine.setCurrentState(CannelGateway.getInstance());
        System.out.println("数据库state  2 -> 3");
    }
}
