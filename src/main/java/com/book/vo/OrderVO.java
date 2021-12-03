package com.book.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private Integer id;
    private String userName;
    private String orderNumber;
    private String address;
    private Float price;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    List<CartVO> cartVOList;

    public List<CartVO> getCartVOList() {
        return cartVOList;
    }

    public void setCartVOList(List<CartVO> cartVOList) {
        this.cartVOList = cartVOList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
