package com.ohgiraffers.sectioc01.model.dao;

import com.ohgiraffers.sectioc01.model.dto.CategoryDTO;
import com.ohgiraffers.sectioc01.model.dto.MenuDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderDTO;
import com.ohgiraffers.sectioc01.model.dto.OrderMenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

// * 순서.7-1
public class OrderDAO {

    // * 순서.12 try~catch 까지
    private Properties prop = new Properties();

    public OrderDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/order-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoryDTO> selectAllCategory(Connection con) {        // * 순서 11-1
        // * 순서.13
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<CategoryDTO> categoryList = null;
        String query = prop.getProperty("selectAllCategory");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            categoryList = new ArrayList<>();

            while (rset.next()) {
                CategoryDTO category = new CategoryDTO();
                category.setCode(rset.getInt("CATEGORY_CODE"));
                category.setName(rset.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return categoryList;
    }


    // * 순서.17-1 -> 17-2 xml 만들기
    public List<MenuDTO> selectMenuByCategoryCode(Connection con, int categoryCode) {

        // * 순서.18
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<MenuDTO> menuList = null;
        String query = prop.getProperty("selectMenuByCategoryCode");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, categoryCode);

            rset = pstmt.executeQuery();

            menuList = new ArrayList<>();

            while (rset.next()) {
                MenuDTO menu = new MenuDTO(rset.getInt("MENU_CODE"),
                        rset.getString("MENU_NAME"),
                        rset.getInt("MENU_PRICE"),
                        rset.getInt("CATEGORY_CODE"),
                        rset.getString("ORDERABLE_STATUS"));
                menuList.add(menu);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }


        return menuList;
    }

    // * 순서.34-1
    public int insertOrder(Connection con, OrderDTO order) {

        // * 순서.41
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertOrder");

        // * 순서.41-2
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, order.getDate());
            pstmt.setString(2, order.getTime());
            pstmt.setInt(3, order.getTotalOrderPrice());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        // * 순서.41-1
        return result;
    }

    // * 순서.37-1
    public int insertOrderMenu(Connection con, OrderMenuDTO orderMenu) {

        // * 순서.42
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertOrderMenu");

            try {
                pstmt = con.prepareStatement(query);

                pstmt.setInt(1, orderMenu.getMenuCode());
                pstmt.setInt(2, orderMenu.getOrderAmount());

                result = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(pstmt);
            }

            return result;

    }
}