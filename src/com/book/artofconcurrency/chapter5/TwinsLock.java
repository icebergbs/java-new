package com.book.artofconcurrency.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * self  define synchronize module
 */
public class TwinsLock implements Lock {
    /**
     * 设计一个同步工具:
     * 该工具在同一时刻,只允许子允许至多两个线程同时访问, 超过两个线程的访问将被阻塞,我们将这个同步工具命名为TwinsLock
     * <p>
     * 允许多个线程访问,共享式访问.
     * 至多两个线程同时访问,资源数为2
     */

    private final Sync sync = new Sync(2);


    /**
     * 自定义同步器 内部类
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);

        }

        @Override
        public int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        public boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                //保证原子性
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }


    /**
     * 自定义同步组件通过组合自定义同步器来完成同步功能
     */
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }


    @Override
    public Condition newCondition() {
        return null;
    }


}
