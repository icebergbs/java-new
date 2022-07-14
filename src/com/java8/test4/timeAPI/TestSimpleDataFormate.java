package com.java8.test4.timeAPI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestSimpleDataFormate {

    public static void main(String[] args) throws Exception {
        //��ͳAPI
		/*ExecutorService pool = Executors.newFixedThreadPool(10);
		
		Callable<Date> task = new Callable<Date>() {

			@Override
			public Date call() throws Exception {
				 
				return   DateFormateThreadLocal.convert("20161218");//��ͳת����ʽ������ThreadLocal
			}
		};
		
		List<Future<Date>> results = new ArrayList<>();
		for (int i = 0; i <  10; i++) {
			results.add(pool.submit(task));
		}
		
		 for (Future<Date> future : results) {
			System.out.println(future.get());
		}
		 
		 pool.shutdown();*/

        ExecutorService pool = Executors.newFixedThreadPool(10);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = new Callable<LocalDate>() {

            @Override
            public LocalDate call() throws Exception {
                //�µ�ת����ʽ    �����������ĸı䶼����һ���µ�ʵ��
                return LocalDate.parse("20161218", dtf);
            }
        };

        List<Future<LocalDate>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        for (Future<LocalDate> future : results) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
