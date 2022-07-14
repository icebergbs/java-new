package com.java8.proxy.jdk;

/**
 * 电能车类，实现Rechargable，Vehicle接口
 *
 * @author bingshan
 * @date 2021/12/21 14:18
 */
public class ElectricCar implements Rechargable, Vehicle {
    @Override
    public void recharge() {
        System.out.println("Electric Car is Moving silently...");
    }

    @Override
    public void drive() {
        System.out.println("Electric Car is Recharging...");
    }
}
