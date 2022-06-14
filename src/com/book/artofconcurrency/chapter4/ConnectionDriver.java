package com.book.artofconcurrency.chapter4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author bingshan
 * @date 2021/10/21 0:04
 */
public class ConnectionDriver {


    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    //创建一个Connection 的代理，在commit时休眠100毫秒
    static final Connection createConnecton() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandler());
    }
}
