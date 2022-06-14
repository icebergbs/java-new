package com.book.jcip.examplestudy.c7;

import com.book.jcip.annotations.GuardedBy;
import com.book.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class PrimeGenerator implements Runnable{


    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();

    /**
     * java 没有一种安全的抢占式方法来停止线程. 只有一些写作式的机制,使请求取消的任务和代码都遵循一种协商好的协议.
     * 一种协作机制:  设置某个"已请求取消"标志,而任务将定期地查看该标志.
     */
    private volatile boolean  cancelled;


    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<>(primes);
    }
}
