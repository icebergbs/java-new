package main.java.com.book.artofconcurrency.chapter10;

import java.util.concurrent.*;

public class FutureTaskTest {

    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();


    /**
     * 当一个线程需要等待另一个线程把某个任务执行完成后它才能继续执行, 可以使用FutureTask
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };
                // 创建任务
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); //执行任务
                }
            }

            try {
                return future.get(); //线程在此等待任务执行完成
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
