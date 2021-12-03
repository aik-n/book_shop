package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.BookDetail;
import com.book.service.BookCategoriesService;
import com.book.service.BookDetailService;
import com.book.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Controller
//@RestController
@RequestMapping("/bookDetail")
public class BookDetailController {

    @Autowired
    private BookDetailService bookDetailService;

    @Autowired
    private BookCategoriesService bookCategoriesService;

    @GetMapping("/list/{level}/{id}")
    public ModelAndView list(@PathVariable("level") String level,@PathVariable("id") Integer id){
        try {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("productList");
            modelAndView.addObject("bookList",bookDetailService.findByCategories(level,id));
            modelAndView.addObject("list",bookCategoriesService.getAllBookCategoriesVO());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/bookCategories/list");
        }
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") Integer id){
        try {
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("productDetail");
            modelAndView.addObject("book",bookDetailService.getById(id));
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/bookCategories/list");
        }

    }

    @GetMapping("/listByName/{name}")
    public ModelAndView listByName(@PathVariable String name){
        try {
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.like("name",name);
            List<BookDetail> bookDetailList=bookDetailService.list(queryWrapper);
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("bookList");
            modelAndView.addObject("searchBook",bookDetailList);
            modelAndView.addObject("list",bookCategoriesService.getAllBookCategoriesVO());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/bookCategories/list");
        }
    }

    @RequestMapping("/data")
    @ResponseBody
    public DataVO list(Integer page,Integer limit){
        return bookDetailService.findData(page, limit);
    }

    @GetMapping("{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

}

