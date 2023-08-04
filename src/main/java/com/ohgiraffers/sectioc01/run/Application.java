package com.ohgiraffers.sectioc01.run;

import com.ohgiraffers.sectioc01.view.OrderMenu;

public class Application {
    public static void main(String[] args) {
        // * 순서.1
        OrderMenu orderMenu = new OrderMenu();

        // * 순서.2
        orderMenu.displayMainMenu();
    }
}
