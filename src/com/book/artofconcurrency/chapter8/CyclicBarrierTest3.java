package com.book.artofconcurrency.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        /**
         * CountDownLatch计数器只能使用一次,
         * CyclicBarrier计数器可以使用reset()方法重置. 能处理更复杂的场景,例如计算错误.可以重置计数器,并让线程重新执行一次.
         * CyclicBarrier还提供其他有用的方法. getNumberWaiting()获得阻塞的线程数量. isBroken()阻塞的线程是否被中断.
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.interrupt();

        try {
            c.await();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException = " + c.isBroken());
            ;
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println("BrokenBarrierException = " + c.isBroken());
            e.printStackTrace();
        }
    }
}
