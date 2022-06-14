package com.book.easycoding.thread.threallocal;

import java.util.concurrent.TimeUnit;

public class M002_InitValueInThreadLocal {

    private static final StringBuilder INIT_VALUE = new StringBuilder("init");

    //��дThreadLocal�� initialValue,  ����StringBuilder��̬����
    private static final ThreadLocal<StringBuilder> builder = new ThreadLocal<StringBuilder>() {
        protected StringBuilder initialValue() {
            return INIT_VALUE;
        }
    };

    private static class AppendStringThread extends Thread {
        @Override
        public void run() {
            StringBuilder inThread = builder.get();
            for (int i = 0; i < 10; i++) {
                inThread.append("_1" + i);
            }
            System.out.println(getName() + ":  " + inThread.toString());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new AppendStringThread().start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
