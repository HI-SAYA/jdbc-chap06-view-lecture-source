package com.ohgiraffers.sectioc01.model.service;

import com.ohgiraffers.sectioc01.model.dao.OrderDAO;
import com.ohgiraffers.sectioc01.model.dto.CategoryDTO;
import com.ohgiraffers.sectioc01.model.dto.MenuDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderMenuDTO;
import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

// * 순서.4-1
public class OrderService {

    // * 순서.7
    private OrderDAO orderDAO = new OrderDAO();

    /* 카테고리 전체 조회용 메소드 */
    public List<CategoryDTO> selectAllCategory() {        // * 순서.5-2
        /* 1. Connection 생성 */
        // * 순서.6
        Connection con = getConnection();

        /* 2. DAO의 모든 카테고리 조회용 메소드를 호출하여 결과를 리턴 받기 */
        // * 순서.8
        List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);   // * 순서.11

        /* 3. Connection 반납 */
        // * 순서.9
        close(con);

        /* 4. 반환 받은 값 리턴 */
        // * 순서.10
        return categoryList;

    }

    // * 순서.16-2
    /* 카테고리별 메뉴 목록 조회용 메소드 */
    public List<MenuDTO> selectMenuByCategoryCode(int categoryCode) {
        // * 순서.17
        Connection con = getConnection();

        List<MenuDTO> menuList = orderDAO.selectMenuByCategoryCode(con, categoryCode);

        close(con);

        return menuList;
    }

    // * 순서.31-1
    /* 주문 정보 등록용 메소드 */
    public int registOrder(OrderDTO order) {
        // * 순서.39
        // 잘됐는지 확인하는 변수
        int result = 0;

        // * 순서.32
        Connection con = getConnection();
        // TBL_ORDER, TBL_ORDER_MENU 둘 다 INSERT 해야한다.
        // 순서 상 TBL_CODE를 먼저 해야하고 (주문 발생) 다음에 TBL_ORDER_MENU가 그 시퀀스 객체값을 참조해야한다. (1번 주문에 짜장 1개, 짬뽕 2개)

        // * 순서.33
        /* TBL_ORDER에 데이터를 삽입하는 메소드 호출 */
        int orderResult = orderDAO.insertOrder(con, order); // * 순서.34
        // 수행이 잘되면 1, 안되면 0 ***


        /* TBL_ORDER_MENU에 데이터를 삽입하는 메소드 호출 */
        // * 순서.36
        int orderMenuResult = 0;
        // * 순서.35
        List<OrderMenuDTO> orderMenuList = order.getOrderMenuList();
        for(OrderMenuDTO orderMenu : orderMenuList) {
           orderMenuResult += orderDAO.insertOrderMenu(con, orderMenu); // * 순서.37
            // * ㄴ 순서.36-1
        }

        // * 순서 38
        /* 모든 로직이 잘 수행 되었는지 판단하여 트랜잭션 처리 */
        if(orderResult > 0 && orderMenuResult == orderMenuList.size()) {
            // *** 2개 행은 insert 잘되고 1개 행은 insert 안되는 경우가 있을 수 있기 때문에 size와 orderResult가 동일한지 판단
            result = 1;
            commit(con);
        } else {
            rollback(con);
        }

        close(con);
        return result;      // * 순서.40
    }


}
