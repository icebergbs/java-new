package com.book.jcip.examplestudy.c7;

import com.book.jcip.examples.FutureRenderer;

import java.util.concurrent.*;

import static com.book.jcip.examples.LaunderThrowable.launderThrowable;

public class TimeRun {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    private static final ScheduledExecutorService taskExec = Executors.newScheduledThreadPool(1);


    /**
     * 在专门的线程中中断任务
     * @param r
     * @param timeout
     * @param unit
     * @throws InterruptedException
     */
    public static void timeRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }

            }

            void rethrow() {
                if (t != null) {
                    throw launderThrowable(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);
        taskThread.join(unit.toMillis(timeout));
        task.rethrow();
    }

    /**
     *  通过Future 来取消任务
     * @param r
     * @param timeout
     * @param unit
     */
    public static void timeRun2(Runnable r, long timeout, TimeUnit unit) {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //如果在任务中抛出了异常, 那么重新抛出该异常
            throw launderThrowable(e.getCause());
        } catch (TimeoutException e) {
            //接下来任务将被取消
        } finally {
            //如果任务已经结束,那么执行取消操作也不会带来任何影响
            task.cancel(true); //如果任务正在进行, 那么将被中断
        }

    }
}
