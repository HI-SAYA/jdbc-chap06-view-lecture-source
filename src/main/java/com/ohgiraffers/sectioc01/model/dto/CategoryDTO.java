package com.ohgiraffers.sectioc01.model.dto;

public class CategoryDTO {

    // * 순서.5
    private int code;
    // int 기본자료형와 Integer 래퍼클래스 의미적 차이 :
    // 기본 자료형과 객체의 차이. 객체는 null값을 가질 수 있다. 기본자료형은 null이 불가능.
    private String name;
    private Integer refCategoryCode;


    // * 순서.6 생성자 / getter, setter / toString 정리
    public CategoryDTO() {
    }

    public CategoryDTO(int code, String name, Integer refCategoryCode) {
        this.code = code;
        this.name = name;
        this.refCategoryCode = refCategoryCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                '}';
    }
}
