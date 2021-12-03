package com.book.service;

import com.book.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.entity.Orders;
import com.book.entity.User;
import com.book.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);

    public List<User> selectById(Integer id);

}   
