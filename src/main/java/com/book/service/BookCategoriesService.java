package com.book.service;

import com.book.entity.BookCategories;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.vo.BookCategoriesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
public interface BookCategoriesService extends IService<BookCategories> {
    public List<BookCategoriesVO> getAllBookCategoriesVO();
}
