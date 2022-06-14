package com.book.jcip.examplestudy.c14;

import com.book.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

@ThreadSafe
public class OneShotLatch {

    private class Sync extends AbstractQueuedLongSynchronizer {
        protected int tryAcquireShared(int ignored) {
            //如果闭锁是开的(state == 1) , 那么这个操作将成功, 否则将失败
            return  (getState() == 1) ? 1 : -1;
        }

        protected boolean tryReleaseShared(int ignored) {
            setState(1); //现在打开闭锁

            return true;   //现在其他的线程可以获取该闭锁
        }
    }
}
