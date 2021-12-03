package com.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public class OrderDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Integer bookId;

    private Integer number;

    private Float price;

    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUser_id(Integer user_id) {
        this.userId = user_id;
    }

    public Integer getBook_id() {
        return bookId;
    }

    public void setBook_id(Integer book_id) {
        this.bookId = book_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    @Override
    public String toString() {
        return "OrderDetail{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", book_id=" + bookId +
        ", number=" + number +
        ", price=" + price +
        ", user_id=" + userId +
        "}";
    }
}
