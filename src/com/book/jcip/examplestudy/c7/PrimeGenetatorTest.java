package com.book.jcip.examplestudy.c7;

import java.math.BigInteger;
import java.util.List;

import static java.util.Calendar.SECOND;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PrimeGenetatorTest {

    /**
     * 一个可取消的任务必须拥有取消策略(Cancellation Policy), 在这个策略中将详细地定义取消操作的"How" "When" 以及 "What" ,
     * 即其他代码如何(How)请求取消该任务, 任务在何时(When) 检查是否已经请求了取消, 以及在响应取消请求时应该执行那些(What) 操作.
     * @return
     */
    List<BigInteger> aSecondOfPrimes() {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();

        try {
            SECONDS.sleep(1);
        } catch (InterruptedException e) {
            generator.cancel();
        }
        return generator.get();
    }
}
