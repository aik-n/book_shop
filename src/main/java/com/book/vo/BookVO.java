package com.book.vo;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class BookVO {

    private Integer id;
    private String name;
    private Float price;

    public BookVO(Integer id, String name, Float price, String fileName) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.fileName=fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

//    public BookVO(Integer id, String name, Integer price, String file_name) {
//    }

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

}
