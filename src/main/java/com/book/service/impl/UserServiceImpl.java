package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.entity.BookDetail;
import com.book.entity.User;
import com.book.mapper.UserMapper;
import com.book.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.vo.BookDetailVO;
import com.book.vo.DataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public DataVO<User> findData(Integer page, Integer limit) {
        DataVO dataVO=new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        IPage<User> userIPage=new Page<>(page,limit);
        IPage<User> result=userMapper.selectPage(userIPage,null);
        dataVO.setCount(result.getTotal());
        List<User> userList=userMapper.selectList(null);

        dataVO.setData(userList);
        return dataVO;
    }
}
