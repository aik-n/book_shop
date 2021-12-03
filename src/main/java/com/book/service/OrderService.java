package com.book.service;

import com.book.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.entity.User;
import com.book.vo.DataVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public interface OrderService extends IService<Orders> {

    public boolean save(Orders orders, User user);

    public DataVO<Orders> findData(Integer page, Integer limit);
}
