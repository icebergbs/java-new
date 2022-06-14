package com.java8.juc.c_012_Volatile;

import java.util.concurrent.TimeUnit;

public class T01_HelloVolatile {

    // volatile
    boolean running = true;

    void m() {
        System.out.print("m start");
        while (running) {

        }
        System.out.println("m end!");
    }


    public static void main(String[] args) {
        T01_HelloVolatile t = new T01_HelloVolatile();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        t.running = false;
    }

}
