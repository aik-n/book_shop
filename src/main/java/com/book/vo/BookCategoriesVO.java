package com.book.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class BookCategoriesVO {
    private Integer id;
    private String name;
    private String bannerImg;
    private List<BookCategoriesVO> children;
    private List<BookVO> bookVOList;

    public BookCategoriesVO(Integer id, String name) {
        this.id=id;
        this.name=name;
    }

    public BookCategoriesVO() {

    }


    public List<BookVO> getBookVOList() {
        return bookVOList;
    }

    public void setBookVOList(List<BookVO> bookVOList) {
        this.bookVOList = bookVOList;
    }


    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

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

    public List<BookCategoriesVO> getChildren() {
        return children;
    }

    public void setChildren(List<BookCategoriesVO> children) {
        this.children = children;
    }


}
