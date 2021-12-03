package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.User;
import com.book.entity.UserDetail;
import com.book.service.UserDetailService;
import com.book.service.UserService;
import com.book.vo.DataVO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;

    /**
     * 登录
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("pwd",password);
        User user=userService.getOne(wrapper);
        if (user == null){
            return "login";
        }
        else{
            session.setAttribute("user",user);
            return "redirect:/bookCategories/list";
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/bookCategories/list";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public String register(User user,Model model){
        boolean result = false;
        try {
            user.setUserImg("tx1.jpg");
            result = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",user.getLoginName()+"已存在！请重新输入！");
            System.out.println("error");
        }
        if (result){
            return "login";
        }
        else{
            return"register";
        }


    }

    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("user");
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("user",user);
        return modelAndView;
    }


    @GetMapping("/userAddress")
    public ModelAndView userAddress(HttpSession httpSession){
        User user=(User) httpSession.getAttribute("user");
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",user.getId());
        List<UserDetail> userDetailList=userDetailService.list(queryWrapper);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("userAddressList");
        modelAndView.addObject("userAddress",userDetailList);
        return modelAndView;
    }

    @GetMapping("/removeAddress/{id}")
    public String removeAddress(@PathVariable("id") Integer id){
        userDetailService.removeById(id);
        return "redirect:/user/userAddress";
    }

    @GetMapping("/updateAddress/{id}/{address}")
    public String updateAddress(@PathVariable("id") Integer id,@PathVariable("address") String address){
        UserDetail userDetail=new UserDetail();
        userDetail.setId(id);
        userDetail.setAddress(address);
        userDetailService.updateById(userDetail);
        return "redirect:/user/userAddress";
    }

    @RequestMapping("/data")
    @ResponseBody
    public DataVO list(Integer page, Integer limit){
        return userService.findData(page, limit);
    }

    @GetMapping("{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @PostMapping("/updatePwd/{oldPwd}/{newPwd}")
    public String updatePwd(HttpSession session,@PathVariable("oldPwd") String oldPwd,@PathVariable("newPwd") String newPwd){
        User user=(User) session.getAttribute("user");
        User user1=new User();
        user1.setId(user.getId());
        user1.setPwd(newPwd);
        userService.updateById(user1);
        session.removeAttribute("user");
        return "login";
    }
}