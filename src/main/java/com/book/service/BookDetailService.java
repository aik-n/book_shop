package com.book.service;

import com.book.entity.BookDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.vo.BookDetailVO;
import com.book.vo.DataVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public interface BookDetailService extends IService<BookDetail> {
    public List<BookDetail> findByCategories(String level,Integer category);

    public DataVO<BookDetailVO> findData(Integer page,Integer limit);
}
