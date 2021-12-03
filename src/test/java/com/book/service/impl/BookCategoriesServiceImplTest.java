package com.book.service.impl;

import com.book.service.BookCategoriesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookCategoriesServiceImplTest {

    @Autowired
    private BookCategoriesService service;

    @Test
    void getAllBookCategoriesVO() {
        service.getAllBookCategoriesVO();
    }
}