package com.book.service;

import com.book.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private  UserService service;

    @Test
    void test(){
        User user=new User();
        user.setLoginName("xxx");
        user.setPwd("000000");
        user.setUserName("测试1号");
        user.setSex("男");
        user.setMail("xwy123000123@22.com");
        user.setPhone("18963526352");
        user.setUserImg("1.jpg");
        System.out.println(service.save(user));
    }
}