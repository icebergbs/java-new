package com.book.artofconcurrency.chapter8;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

    /**
     * Exchanger 用于线程间协作的工具类.
     * 用于进行线程间的数据交换.它提供一个同步点,在这个同步点,两个线程可以交换彼此的数据.
     * 应用场景:
     * 可用于遗传算法.遗传算法需要两个人作为交配对象,这时候交换两个人的数据,并使用交叉规则得出2个交配结果.
     * 可用于校对工作. 比如把纸制流水通过人工的方式录入电子银行流水,为了避免错误,采用AB岗两人录入,录入Excel,系统加载两个Excel, 并对两个Excel数据进行校对,看是否一致
     */

    private static final Exchanger<String> exgr = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String A = "银行流水A";
                try {
                    exgr.exchange(A);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String B = "银行流水B";
                try {
                    String A = exgr.exchange(B);
                    System.out.println("A和B数据是否一致: " + A.equals(B) + ",  A录入的是: " + A + ", B录入的是: " + B);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();

    }
}
