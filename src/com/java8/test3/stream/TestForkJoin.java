package com.java8.test3.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import org.junit.Test;

public class TestForkJoin {

    @Test
    public void test1() {
        Instant start = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoin(0, 10000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }

    /*
     * Java8 ������
     * */
    @Test
    public void test2() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0, 10000000000L)
                .parallel()
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());


    }
}
