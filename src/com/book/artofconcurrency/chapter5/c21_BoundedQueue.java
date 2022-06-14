package com.book.artofconcurrency.chapter5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过有界队列的方式,深入了解Condition的使用方式
 * 有界队列:
 * 一种特殊的队列,当队列为空时,队列的获取操作将会阻塞获取线程,直到队列中有新增元素,当队列已满时,队列的插入操作将会阻塞插入线程,直到队列出现空位
 */
public class c21_BoundedQueue<T> {

    private Object[] items;
    //添加的下标,删除的下标,和数组当前数量
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public c21_BoundedQueue(int size) {
        this.items = new Object[size];
    }

    //添加一个元素,如果数组满,则添加线程进入等待状态,直到有空位
    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            if (++addIndex == items.length) {
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    //由头部删除一个元素,如果数组空,则删除线程进入等待状态,直到有新添加元素
    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }
}
