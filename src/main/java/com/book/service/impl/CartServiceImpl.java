package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.BookDetail;
import com.book.entity.Cart;
import com.book.entity.Orders;
import com.book.entity.User;
import com.book.mapper.BookDetailMapper;
import com.book.mapper.CartMapper;
import com.book.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.vo.CartVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BookDetailMapper bookDetailMapper;

    @Override
    public List<CartVO> findAllCartVOByUserId(Integer id) {
        List<CartVO> cartVOList=new ArrayList<>();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Cart> cartList=cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO=new CartVO();
            cartVO.setCartId(cart.getId());
            cartVO.setCount(cart.getCount());
            cartVO.setAllPrice(cart.getPrice()*cart.getCount());
            BookDetail bookDetail=bookDetailMapper.selectById(cart.getBookId());
            BeanUtils.copyProperties(bookDetail,cartVO);
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }

    @Override
    public List<User> selectById(Integer id){
        return null;
    }




}
