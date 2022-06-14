package com.book.artofconcurrency.chapter5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 通过一个缓存的示例说明读写锁的使用方式
 */
public class Cache {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    static Lock r = rwl.readLock();
    static Lock w = rwl.writeLock();

    //获取一个key 对应的value
    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    //设置key对应的value, 并返回旧的value
    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    //清空所有的内容
    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }

    /**
     * 锁降级:
     * 写锁降级为读锁.
     * 把持住(当前拥有的)写锁,在获取到读锁,随后释放(先前拥有的)写锁的过程
     * <p>
     * 降级中读锁的获取是否必要?
     * 是必要的. 主要是为了保证数据的可见性,如果当前线程不获取读锁而是直接释放写锁,
     * 假设此刻另一个线程(记作线程T) 获取了写锁并修改数据,那么当前线程无法感知线程T的数据更新.
     * 如果当前线程获取读锁,即遵守锁降级的步骤,则线程T将会被阻塞,
     * 直到当前线程使用数据并释放读锁子后,线程T才能获取写锁进行数据更新.
     */
    volatile boolean update = false;

    public void processData() {
        r.lock();
        if (!update) {
            //必须先释放读锁
            r.unlock();
            //锁降级从写锁获取到开始
            w.lock();
            try {
                if (!update) {
                    //准备数据的流程
                    update = true;
                }
                r.lock();
            } finally {
                w.unlock();
            }
            //锁降级完成,写锁降级为读锁
        }

        try {
            //使用数据的流程
        } finally {
            r.unlock();
        }

    }
}
