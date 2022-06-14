package com.book.artofconcurrency.chapter4;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author bingshan
 * @date 2021/10/20 23:29
 */
public class ConnectionPool {
    private LinkedList<Connection> pools = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pools.addLast(ConnectionDriver.createConnecton());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pools) {
                //连接释放后需要进行通知， 这样其他消费者能够感知到连接池中已经归还了一个链接
                pools.addLast(connection);
                pools.notifyAll();
            }
        }
    }

    //在mills 内无法获取到连接，将会返回null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pools) {
            //完全超时
            if (mills <= 0) {
                while (pools.isEmpty()) {
                    pools.wait();
                }
                return pools.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pools.isEmpty() && remaining > 0) {
                    pools.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pools.isEmpty()) {
                    result = pools.removeFirst();
                }
                return result;
            }

        }
    }
}
