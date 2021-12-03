package com.book.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.Cart;
import com.book.entity.Orders;
import com.book.entity.User;
import com.book.service.CartService;
import com.book.service.OrderService;
import com.book.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserDetailService userDetailService;



    @GetMapping("/add/{bookId}/{bookPrice}/{quantity}")
    public ModelAndView add(@PathVariable("bookId") Integer bookId, @PathVariable("bookPrice") Float bookPrice, @PathVariable("quantity") Integer quantity, HttpSession session){
        try {
            User user=(User) session.getAttribute("user");
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("book_id",bookId);
            queryWrapper.eq("user_id",user.getId());

            if (cartService.getOne(queryWrapper)==null){
                Cart cart=new Cart();
                cart.setBookId(bookId);
                cart.setCount(quantity);
                cart.setPrice(bookPrice);

                ModelAndView modelAndView=new ModelAndView();
                cart.setUserId(user.getId());
                //信息存到cart表中
                if (cartService.save(cart)){
                    modelAndView.setViewName("redirect:/cart/findAllCart");
                }
                return modelAndView;
            }else{
                QueryWrapper<Cart> queryWrapper1=new QueryWrapper<>();
                //queryWrapper.eq("book_id",bookId);
                queryWrapper1.and(
                        wrapper ->
                                wrapper.eq("book_id", bookId).eq("user_id", user.getId()));

                //queryWrapper.and(Wrapper -> Wrapper.eq("first_name", "mama").and().eq("last_name ","mama"));
//                queryWrapper.ne("user_id",user.getId()).eq();

//                QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
//                Map<String, Object> queryParamsMap = new HashMap<>();
//                queryParamsMap.put("book_id", bookId);
//                queryParamsMap.put("user_id", user.getId());
//                queryWrapper.allEq(queryParamsMap);

                //QueryWrapper sql="select * from cart where book_id="+bookId+"user_id="+user.getId()+"";

                Cart cart=cartService.getOne(queryWrapper1);

                Cart cart1=new Cart();
                cart1.setId(cart.getId());
                cart1.setCount(quantity+cart.getCount());
                cartService.updateById(cart1);
                return new ModelAndView("redirect:/cart/findAllCart");
            }

        }
        catch (Exception e){
//          model.addAttribute("msg","你还未登录，请先进行登录！");
//          JOptionPane.showMessageDialog(null, "标题【错误】", "格式输入错误", JOptionPane.ERROR_MESSAGE);
            return new ModelAndView("redirect:/login");
        }

    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("settlement1");
            modelAndView.addObject("list",cartService.findAllCartVOByUserId(user.getId()));
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping ("/updateCart/{id}/{quantity}/{cost}")
    public String updateCart(@PathVariable("id") Integer id,@PathVariable("quantity") Integer quantity,@PathVariable("cost") Float cost){
        try {
            Cart cart=new Cart();
            cart.setId(id);
            cart.setCount(quantity);
            cartService.updateById(cart);
            return "redirect:/cart/findAllCart";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping("removeCart/{id}")
    public String removeCart(@PathVariable("id") Integer id){
        try {
            cartService.removeById(id);
            return "redirect:/cart/findAllCart";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/settlement2")
    public ModelAndView settlement2(HttpSession session){
        try {
            User user=(User)session.getAttribute("user");
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("settlement2");
            //modelAndView.addObject("userList",userService.getById(user.getId()));
            modelAndView.addObject("list",cartService.findAllCartVOByUserId(user.getId()));

            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("user_id",user.getId());
            modelAndView.addObject("address",userDetailService.list(queryWrapper));

            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/login");
        }

    }


}