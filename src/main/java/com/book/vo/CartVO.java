package com.book.vo;


import lombok.Data;

@Data
public class CartVO {
    private Integer cartId;
    private Integer count;
    private Float AllPrice;
    private String name;
    private Float price;
    private String fileName;
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer stock) {
        this.number = stock;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getAllPrice() {
        return AllPrice;
    }

    public void setAllPrice(Float allPrice) {
        AllPrice = allPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
