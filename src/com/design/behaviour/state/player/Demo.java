package com.design.behaviour.state.player;

/**
 * @author bingshan
 * @date 2021/10/17 23:34
 */
public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }
}
