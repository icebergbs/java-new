package com.design.behaviour.observer.case4;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class DefEventBus {
    private Executor executor;
    private ObserverRegistry registry = new ObserverRegistry();

    public DefEventBus() {
        this(MoreExecutors.directExecutor());
    }

    protected DefEventBus(Executor executor) {
        this.executor = executor;
    }

    public void register(Object object) {
        registry.register(object);
    }

    public void post(Object event) {
        List<ObserverAction> observerActions = registry.getMatchObserverActions(event);
        for (ObserverAction observerAction : observerActions) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    observerAction.execute(event);
                }
            });
        }
    }
}
