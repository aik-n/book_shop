package com.book.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.entity.Orders;
import com.book.entity.User;
import com.book.mapper.OrderMapper;
import com.book.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.vo.DataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public boolean save(Orders orders, User user) {
        orders.setUserId(user.getId());
        orders.setUserName(user.getUserName());
        orders.setCreateTime(LocalDateTime.now());
        String number=null;
        try{
            StringBuffer numberList=new StringBuffer();
            for (int i=0;i<32;i++){
                numberList.append(Integer.toHexString(new Random().nextInt(16)));
            }
            number=numberList.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        orders.setNumber(number);
        return save(orders);
    }

    @Override
    public DataVO<Orders> findData(Integer page, Integer limit) {
        DataVO dataVO=new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<Orders> ordersIPage=new Page<>(page,limit);
        IPage<Orders> result=orderMapper.selectPage(ordersIPage,null);
        dataVO.setCount(result.getTotal());
        List<Orders> ordersList=orderMapper.selectList(null);

        dataVO.setData(ordersList);
        return dataVO;
    }
}
