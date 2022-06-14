package com.book.artofconcurrency.chapter8;


import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行流水处理服务
 */
public class BankWaterService implements Runnable {


    /**
     * CyclicBarrier可以用于多线程计算数据,最后合并计算结果的场景
     * 例如:一个Excel保存了用户所有银行流水,每个Sheet保存了一个账户近一年的每笔流水,
     * 现在需要统计用户的日均银行流水,先用多线程处理每个sheet里的银行流水,
     * 最后,在用barrierAction计算整个Excel的日均银行流水.
     */

    //创建4个屏障, 处理完子后执行当前类的run方法
    private CyclicBarrier c = new CyclicBarrier(4, this);

    //
    private Executor executor = Executors.newFixedThreadPool(4);

    //保存每个sheet计算银流结果
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();


    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //计算当前银流数据,计算代码省略
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    //计算完成,插入一个屏障
                    try {
                        c.await();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        //汇总每个sheet计算结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        //
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }


    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}
