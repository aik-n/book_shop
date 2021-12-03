package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.Img;
import com.book.service.BookCategoriesService;
import com.book.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Controller
@RequestMapping("/bookCategories")
public class BookCategoriesController {

    @Autowired
    private BookCategoriesService bookCategoriesService;
    @Autowired
    private ImgService imgService;

    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list",bookCategoriesService.getAllBookCategoriesVO());
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("month_tag",month);
        List<Img> imgList=imgService.list(queryWrapper);
        modelAndView.addObject("img",imgList);
        return modelAndView;
    }
}

