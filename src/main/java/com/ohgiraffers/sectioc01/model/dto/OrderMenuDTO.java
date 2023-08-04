package com.ohgiraffers.sectioc01.model.dto;

public class OrderMenuDTO {
    // * 순서.20 --------------

    private int code;
    private int menuCode;
    private int orderAmount;

    public OrderMenuDTO() {
    }

    public OrderMenuDTO(int code, int menuCode, int orderAmount) {
        this.code = code;
        this.menuCode = menuCode;
        this.orderAmount = orderAmount;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenuDTO{" +
                "code=" + code +
                ", menuCode=" + menuCode +
                ", orderAmount=" + orderAmount +
                '}';
    }
}

