package com.book.artofconcurrency.chapter6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Integer> {

    public static void main(String[] args) {

        /**
         * Fork/Join
         * java7提供的一个用于并行执行任务的框架.
         * Fork就算把一个大任务切分为若干子任务并行执行,
         * Join就是合并这些子任务的执行结果最后得到一个大任务的结果.
         *
         * 工作窃取算法:
         *     某个线程从其他队列里窃取任务来执行
         * 为什么需要使用工作窃取算法:
         *     做一个比较大的任务,把任务分割为若干个互不相干的子任务,为了减少线程间的竞争,把子任务分别放在不同的队列里,并为每个队列创建一个单独的线程,线程和队列一一对应.
         *     有的线程先干完,就去其他线程的队列里窃取一个任务.而在这时他们会访问同一个队列,所以为了减少窃取任务线程和被窃取任务线程之间的竞争,通常会使用双端队列,窃取任务永远从双端队列的尾部拿
         *
         * 优点:
         *    充分利用线程进行并行计算,减少了线程间的竞争
         * 缺点:
         *    某些情况下存在竞争,比如有一个任务时.
         *     会消耗更多的系统资源,比如创建多个线程和多个双端队列
         */


        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1, 6);

        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static final int THRESHOLD = 2; //伐值
    private int start;
    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = start + (end - start) / 2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightRresult = rightTask.join();
            sum = leftResult + rightRresult;
        }
        return sum;
    }
}
