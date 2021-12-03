package com.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public class BookDetail implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private Float price;

    private String author;

    private String publish;

    private String fileName;

    private Integer categoryOne;

    private Integer number;

    private String detailFile;

    private String describ;

    private Integer sellCount;

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public String getDetailFile() {
        return detailFile;
    }

    public void setDetailFile(String detailFile) {
        this.detailFile = detailFile;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCategoryOne() {
        return categoryOne;
    }


    public void setCategoryOne(Integer categoryOne) {
        this.categoryOne = categoryOne;
    }

    public Integer getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(Integer categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    private Integer categoryTwo;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
        "id=" + id +
        ", name=" + name +
        ", price=" + price +
        ", author=" + author +
        ", publish=" + publish +
        ", createTime=" + createTime +
        ", categoryOne=" + categoryOne +
        ", categoryTwo=" + categoryTwo +
        ", fileName=" + fileName +
        ", number=" + number +
        "}";
    }
}
