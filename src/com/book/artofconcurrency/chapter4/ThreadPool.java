package com.book.artofconcurrency.chapter4;

/**
 * 一个简单的线程池接口定义
 *
 * @author bingshan
 * @date 2021/10/23 13:31
 */
public interface ThreadPool<Job extends Runnable> {
    //执行一个Job  这个Job要实现Runnable
    void execute(Job job);

    //关闭线程池
    void shutdown();

    //增加工作者线程
    void addWorkers(int num);

    //减少工作者线程
    void removeWorker(int num);

    //得到正在执行的任务数量
    int getJobSize();
}
