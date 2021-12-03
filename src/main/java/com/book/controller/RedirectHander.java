package com.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RedirectHander {

    @GetMapping("{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }


    @GetMapping("/")
    public String main(){
        return "redirect:/bookCategories/list";
    }

//    @GetMapping("/admin")
//    public String main1(){
//        return "redirect:/bookCategories/list";
//    }
}
