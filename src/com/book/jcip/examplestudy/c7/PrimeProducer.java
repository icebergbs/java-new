package com.book.jcip.examplestudy.c7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread{

    /**
     * 线程中断是一种协作机制, 线程可以通过这种机制来通知另一个线程, 告诉他在合适或者可能的情况下停止当前工作.
     *
     * public void interrupt() {...}   //中断目标线程
     * public boolean isInterrupted() {...} 返回中断状态
     * public static boolean interrupted() {...} //清除当前线程的中断状态
     *
     *  通常,  中断是实现取消的最合理方式
     */
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                queue.put(p = p.nextProbablePrime());
            } catch (InterruptedException e) {
                //允许线程退出
            }
        }
    }

    public void cancel() {
        interrupt();
    }
}
