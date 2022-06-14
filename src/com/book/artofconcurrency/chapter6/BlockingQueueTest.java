package com.book.artofconcurrency.chapter6;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

public class BlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue failQueue = new ArrayBlockingQueue(1000, true);
        LinkedBlockingDeque linkQueue = new LinkedBlockingDeque();
        //支持优先级的无界阻塞队列
        PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue();
        //支持延时获取元素的无界队列
        DelayQueue delayQueue = new DelayQueue();
        /**
         * 1. 如何实现Delayed接口
         * 参考ScheduledThreadPoolExecutor里ScheduledFutureTask类的实现,一共三步
         *      1.初始化基本数据
         *      2.实现getDelay()
         *      3.实现compareTo方法来指定元素的顺序
         * 2.如何实现延迟阻塞队列
         *     消费者从队列里获取元素时,如果元素没有到达延时时间,就阻塞当前线程
         */


    }
}
