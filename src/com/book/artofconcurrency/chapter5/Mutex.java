package com.book.artofconcurrency.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁示例
 * 独占锁： 在同一时刻只能有一个线程获得到锁，而其他获取的线程只能处于同步队列中等待，
 * 只有获取锁的线程释放了锁，后继的线程才能获取锁
 *
 * @author bingshan
 * @date 2021/10/24 20:03
 */
public class Mutex implements Lock {

    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //当状态为0的时候获取锁
        public boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //释放锁， 将状态设置为 0
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //返回一个Condition, 每个Condition 都包含了一个condition队列
        Condition newCondition() {
            return new ConditionObject();
        }
    }


    //仅需将操作代理到Sync 上即可
    private final Sync sync = new Sync();


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }


}
