package com.design.behaviour.state.gateway;

public class CannelGateway extends AbstractGateway {

    private static final CannelGateway instance = new CannelGateway();

    private CannelGateway() {
    }

    ;

    public static CannelGateway getInstance() {
        return instance;
    }

    public GatewayState getState() {
        return GatewayState.CANCEL;
    }

    @Override
    public void startEvent(GatewayStateMachine stateMachine) {
        //TODO 数据库state  3 -> 1
        stateMachine.setCurrentState(CannelGateway.getInstance());
        System.out.println("数据库state  3 -> 1");
    }
}
