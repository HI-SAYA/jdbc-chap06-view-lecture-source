package com.ohgiraffers.sectioc01.model.dto;

import java.util.List;

// * 순서.26-1
public class OrderDTO {

    // * 순서.27 필드, 생성자, 매개변수 생성자, getter / setter / toString 생성
    private int code;
    private String date;
    private String time;
    private int totalOrderPrice;
    // 세부적으로 주문 목록을 가진다. (이 메뉴를 몇개 주문했는가 -OrderMenuDTO)
    private List<OrderMenuDTO> orderMenuList;

    public OrderDTO() {}

    public OrderDTO(int code, String date, String time, int totalOrderPrice, List<OrderMenuDTO> orderMenuList) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.totalOrderPrice = totalOrderPrice;
        this.orderMenuList = orderMenuList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public List<OrderMenuDTO> getOrderMenuList() {
        return orderMenuList;
    }

    public void setOrderMenuList(List<OrderMenuDTO> orderMenuList) {
        this.orderMenuList = orderMenuList;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "code=" + code +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                ", orderMenuList=" + orderMenuList +
                '}';
    }
}
