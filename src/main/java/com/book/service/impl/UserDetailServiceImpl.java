package com.book.service.impl;

import com.book.entity.UserDetail;
import com.book.mapper.UserDetailMapper;
import com.book.service.UserDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements UserDetailService {

}
