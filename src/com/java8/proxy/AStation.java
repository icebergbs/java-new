package com.java8.proxy;

/**
 * @author bingshan
 * @date 2021/12/20 20:05
 */
public class AStation implements ATicketService {
    @Override
    public void sellTicket() {
        System.out.println("\n\t售票.....\n");
    }

    @Override
    public void inquire() {
        System.out.println("\n\t问询。。。。\n");
    }

    @Override
    public void withdraw() {
        System.out.println("\n\t退票......\n");
    }
}
