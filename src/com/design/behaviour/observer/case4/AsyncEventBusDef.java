package com.design.behaviour.observer.case4;



import java.util.concurrent.Executor;

public class AsyncEventBusDef extends DefEventBus {
    public AsyncEventBusDef(Executor executor) {
        super(executor);
    }
}
