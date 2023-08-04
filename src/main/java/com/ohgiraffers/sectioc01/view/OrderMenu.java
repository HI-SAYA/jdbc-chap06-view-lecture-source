package com.ohgiraffers.sectioc01.view;

import com.ohgiraffers.sectioc01.model.dto.CategoryDTO;
import com.ohgiraffers.sectioc01.model.dto.MenuDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderMenuDTO;
import com.ohgiraffers.sectioc01.model.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// * 순서.1-1
public class OrderMenu {

    // * 순서.4
    private OrderService orderService = new OrderService();
    public void displayMainMenu() {
        // * 순서.2-1

        /* ------------ 반복 ----------------------
         * 1. 카테고리 조회
         * 2. 해당 카테고리의 메뉴 조회
         * 3. 사용자에게 어떤 메뉴를 주문 받을 것인지 입력
         * 4. 주문할 수량 입력
         * ---------------------------------------
         * 5. 주문
         * */

        // * 순서.22
        List <OrderMenuDTO> orderMenuList = new ArrayList<>();
        int totalOrderPrice = 0;

        // * 순서.3
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("=========== 음식 주문 프로그램 ===========");

            /* 1. 카테고리 조회 후 출력 */
            // * 순서.5
            List<CategoryDTO> categoryList = orderService.selectAllCategory(); // * 순서.5-1
            for(CategoryDTO category : categoryList) {
                System.out.println(category.getName());
            }

            // * 순서.14
            System.out.println("=======================================");
            System.out.print("주문하실 카테고리를 선택해주세요 : ");
            String inputCategory = sc.nextLine();

            // * 순서.15
            // 조회하려고 하는 카테고리 코드를 뽑아왔다.
            int categoryCode = 0;
            for(CategoryDTO category : categoryList) {
                if(category.getName().equals(inputCategory)) {
                    categoryCode = category.getCode();
                }
            }

            // * 순서.16
            System.out.println("=========== 주문 가능 메뉴 ============");
            List<MenuDTO> menuList = orderService.selectMenuByCategoryCode(categoryCode); // * 순서.16-1
            for(MenuDTO menu : menuList) {
                System.out.println(menu.getName() + " : " + menu.getPrice() + "원");
            }


            // * 순서.19
            System.out.print("주문하실 메뉴를 선택해주세요 : ");
            String inputMenu = sc.nextLine();

            int menuCode = 0;
            int menuPrice = 0;
            for(MenuDTO menu : menuList) {
                if(menu.getName().equals(inputMenu)) {
                    menuCode = menu.getCode();
                    menuPrice = menu.getPrice();
                }
            }

            System.out.print("주문하실 수량을 입력하세요 : ");
            int orderAmount = sc.nextInt();

            // * 순서.21
            OrderMenuDTO orderMenu = new OrderMenuDTO();
            orderMenu.setMenuCode(menuCode);
            orderMenu.setOrderAmount(orderAmount);      // 코드와 수량 저장

            // * 순서.23
            orderMenuList.add(orderMenu);
            totalOrderPrice += (menuPrice * orderAmount);

            System.out.print("계속 주문 하시겠습니까? (예/아니오) : ");
            sc.nextLine(); // 버퍼 삭제
            boolean isContinue = sc.nextLine().equals("예");

            if(!isContinue) break;

        } while(true); // * 순서.?


        // * 순서.24
        System.out.println("========== 주문 목록 확인 ==========");
        for(OrderMenuDTO orderMenu : orderMenuList) {
            System.out.println(orderMenu);
        }

        // * 순서.25
        Date orderTime = new Date();        // new Date 현재 시스템의 시간
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd"); // 년월일 java.text패키지 하위에 있는 SimpleDateFormat 사용
        SimpleDateFormat timeFormet = new SimpleDateFormat("HH:mm:ss"); // 시분초
        String date = dateFormat.format(orderTime);
        String time = timeFormet.format(orderTime);

        // * 순서.26
        OrderDTO order = new OrderDTO();

        //* 순서.28
        order.setTime(time);
        order.setDate(date);
        order.setTotalOrderPrice(totalOrderPrice);
        order.setOrderMenuList(orderMenuList);

        // * 순서.29
        /* 주문 내역을 전달하여 데이터베이스에 저장 */
        int result = orderService.registOrder(order); // * 순서.31

        // * 순서.30
        if(result > 0) {
            System.out.println("주문이 완료 되었습니다.");
        } else {
            System.out.println("주문을 실패하였습니다.");
        }
    }
}