package com.book.service;

import com.book.vo.DataVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookDetailServiceTest {

    @Autowired
    private BookDetailService service;

    @Test
    void test(){
        Map<String,Object> map=new HashMap<>();
        map.put("category",5);
        service.listByMap(map).forEach(System.out::println);
    }

    @Test
    void getById(){
        System.out.println(service.getById(8));
    }

    @Test
    void findData(){
//        DataVO dataVO=service.findData();
        int i=1;
    }


}