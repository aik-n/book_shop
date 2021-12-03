package com.book.service;

import com.book.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.vo.BookDetailVO;
import com.book.vo.DataVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public interface UserService extends IService<User> {

    public DataVO<User> findData(Integer page, Integer limit);
}
