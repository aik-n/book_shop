package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.*;
import com.book.mapper.BookDetailMapper;
import com.book.mapper.CartMapper;
import com.book.mapper.OrderMapper;
import com.book.service.*;
import com.book.vo.CartVO;
import com.book.vo.DataVO;
import com.book.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private BookDetailService bookDetailService;
    @Autowired
    private BookDetailMapper bookDetailMapper;


    @PostMapping("/settlement2")
    public ModelAndView settlement3(HttpSession session, String address, Float price){

        User user=(User)session.getAttribute("user");

        Orders orders =new Orders();
        String address1=null;
        if (address.substring(0,10).equals("newAddress"))
        {
            address1=address.substring(11);
            UserDetail userDetail=new UserDetail();
            userDetail.setUserId(user.getId());
            userDetail.setAddress(address1);
            userDetailService.save(userDetail);
        }else{
            address1=address;
        }
        orders.setAddress(address1);
        orders.setPrice(price);
        orders.setUserId(user.getId());
        orders.setUserName(user.getUserName());
        orders.setCreateTime(LocalDateTime.now());
        String number = null;
        try {
            StringBuffer numberList = new StringBuffer();
            for (int i = 0; i < 32; i++) {
                numberList.append(Integer.toHexString(new Random().nextInt(16)));
            }
            number = numberList.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        orders.setNumber(number);
        orderService.save(orders);
        //取商品列表
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id",user.getId());
        List<Cart> cartList=cartService.list(queryWrapper);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrderId(orders.getId());
            orderDetail.setBook_id(cart.getBookId());
            orderDetail.setNumber(cart.getCount());
            orderDetail.setPrice(cart.getPrice());
            orderDetail.setUser_id(user.getId());
            orderDetailService.save(orderDetail);
            //减库存
            BookDetail bookDetail=new BookDetail();
            bookDetail.setId(cart.getBookId());
            BookDetail bookDetail1=new BookDetail();
            QueryWrapper queryWrapper1=new QueryWrapper();
            queryWrapper1.eq("id",cart.getBookId());
            bookDetail1=bookDetailService.getOne(queryWrapper1);
            bookDetail.setNumber(bookDetail1.getNumber()-cart.getCount());
            bookDetail.setSellCount(bookDetail1.getSellCount()+cart.getCount());
            bookDetailService.updateById(bookDetail);
        }
        //清购物车
        QueryWrapper queryWrapper1=new QueryWrapper();
        queryWrapper1.eq("user_id",user.getId());
        cartService.remove(queryWrapper1);


        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("list",orders);
        return modelAndView;
    }

    @GetMapping("userOrder")
    public ModelAndView userOrder(HttpSession httpSession){
        try {
            User user=(User) httpSession.getAttribute("user");
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("user_id",user.getId());
            List<Orders> ordersList=orderService.list(queryWrapper);
            List<OrderVO> orderVOList=new ArrayList<>();
            for (Orders orders : ordersList) {
                OrderVO orderVO=new OrderVO();
                orderVO.setId(orders.getId());
                orderVO.setUserName(user.getUserName());
                orderVO.setAddress(orders.getAddress());
                orderVO.setOrderNumber(orders.getNumber());
                orderVO.setPrice(orders.getPrice());
                if (orders.getStatus()==0){
                    orderVO.setStatus("待处理");
                }else{
                    orderVO.setStatus("已完成");
                }
                orderVOList.add(orderVO);
            }
            for (OrderVO orderVO : orderVOList) {
                QueryWrapper queryWrapper1=new QueryWrapper();
                queryWrapper1.eq("order_id",orderVO.getId());
                List<OrderDetail> orderDetailList=orderDetailService.list(queryWrapper1);
                List<CartVO> cartVOList=new ArrayList<>();
                for (OrderDetail orderDetail : orderDetailList) {
                    CartVO cartVO=new CartVO();
                    cartVO.setAllPrice(orderDetail.getPrice()*orderDetail.getNumber());
                    cartVO.setPrice(orderDetail.getPrice());
                    cartVO.setCount(orderDetail.getNumber());
                    BookDetail bookDetail=bookDetailMapper.selectById(orderDetail.getBook_id());;
                    cartVO.setName(bookDetail.getName());
                    cartVO.setFileName(bookDetail.getFileName());
                    cartVO.setNumber(1);
                    cartVO.setCartId(1);
                    cartVOList.add(cartVO);
                }
                orderVO.setCartVOList(cartVOList);
            }
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("orderList");
            modelAndView.addObject("orderList",orderVOList);
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping("/data")
    @ResponseBody
    public DataVO list(Integer page, Integer limit){
        return orderService.findData(page, limit);
    }

    @GetMapping("{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @PostMapping("/deleteOrder/{id}")
    public void deleteOrder(@PathVariable("id") Integer id){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("order_id",id);
        orderDetailService.remove(queryWrapper);
        orderService.removeById(id);
    }

    @PostMapping("/updateOrder/{id}")
    public void updateOrder(@PathVariable("id") Integer id){
        Orders orders=new Orders();
        orders.setId(id);
        orders.setStatus(1);
        orderService.updateById(orders);
    }
}

