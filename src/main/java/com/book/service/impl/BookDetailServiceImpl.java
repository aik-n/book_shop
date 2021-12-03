package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.entity.BookDetail;
import com.book.mapper.BookCategoriesMapper;
import com.book.mapper.BookDetailMapper;
import com.book.service.BookDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.vo.BookDetailVO;
import com.book.vo.DataVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */
@Service
public class BookDetailServiceImpl extends ServiceImpl<BookDetailMapper, BookDetail> implements BookDetailService {

    @Autowired
    private BookDetailMapper bookDetailMapper;
    @Autowired
    private BookCategoriesMapper bookCategoriesMapper;

    @Override
    public List<BookDetail> findByCategories(String level,Integer category) {
        Map<String,Object> map=new HashMap<>();
        map.put("category_"+level,category);
        return bookDetailMapper.selectByMap(map);
    }
    @Override
    public DataVO<BookDetailVO> findData(Integer page,Integer limit) {
        DataVO dataVO=new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");

        IPage<BookDetail> bookDetailIPage=new Page<>(page,limit);
        IPage<BookDetail> result=bookDetailMapper.selectPage(bookDetailIPage,null);
        dataVO.setCount(result.getTotal());
        List<BookDetail> bookDetailList=result.getRecords();
        List<BookDetailVO> bookDetailVOList=new ArrayList<>();
        for (BookDetail bookDetail : bookDetailList) {
            BookDetailVO bookDetailVO=new BookDetailVO();
            BeanUtils.copyProperties(bookDetail,bookDetailVO);
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("id",bookDetail.getCategoryOne());
            bookDetailVO.setCategoryOne(bookCategoriesMapper.selectOne(queryWrapper).getName());
            QueryWrapper queryWrapper1=new QueryWrapper();
            queryWrapper1.eq("id",bookDetail.getCategoryTwo());
            bookDetailVO.setCategoryOne(bookCategoriesMapper.selectOne(queryWrapper1).getName());
            bookDetailVOList.add(bookDetailVO);
        }
        dataVO.setData(bookDetailVOList);
        return dataVO;
    }
}
