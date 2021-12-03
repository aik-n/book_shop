package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.BookDetail;
import com.book.entity.Manager;
import com.book.entity.User;
import com.book.service.BookDetailService;
import com.book.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private BookDetailService bookDetailService;

    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("pwd",password);
        Manager manager=managerService.getOne(wrapper);
        if (manager == null){
            return "managerLogin";
        }
        else{
            session.setAttribute("manager",manager);
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("manager");
        return "redirect:/managerLogin";
    }

    @PostMapping("/addBook/{name}/{price}/{author}/{publish}/{date1}/{categoryOne}/{categoryTwo}/{fileName}/{detailFile}/{number}/{describ}")
    public void addBook(@PathVariable("name") String name,
                          @PathVariable("price") Float price,
                          @PathVariable("author") String author,
                          @PathVariable("publish") String publish,
                          @PathVariable("date1") String date1,
                          @PathVariable("categoryOne") Integer categoryOne,
                          @PathVariable("categoryTwo") Integer categoryTwo,
                          @PathVariable("fileName") String fileName,
                          @PathVariable("detailFile") String detailFile,
                          @PathVariable("number") Integer number,
                          @PathVariable("describ") String describ){
        BookDetail bookDetail=new BookDetail();
        bookDetail.setName(name);
        bookDetail.setPrice(price);
        bookDetail.setAuthor(author);
        bookDetail.setPublish(publish);
        LocalDateTime parse = LocalDateTime.parse(date1+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        bookDetail.setCreateTime(parse);
        bookDetail.setCategoryOne(categoryOne);
        bookDetail.setCategoryTwo(categoryTwo);
        bookDetail.setFileName(fileName);
        bookDetail.setDetailFile(detailFile);
        bookDetail.setNumber(number);
        bookDetail.setDescrib(describ);
        bookDetail.setSellCount(0);
        bookDetailService.save(bookDetail);


    }
}

