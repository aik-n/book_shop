package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.book.entity.BookCategories;
import com.book.entity.BookDetail;
import com.book.mapper.BookCategoriesMapper;
import com.book.mapper.BookDetailMapper;
import com.book.service.BookCategoriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.vo.BookCategoriesVO;
import com.book.vo.BookVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xwy
 * @since 2021-03-15
 */

@Service
public class BookCategoriesServiceImpl extends ServiceImpl<BookCategoriesMapper, BookCategories> implements BookCategoriesService {

    @Autowired
    private BookCategoriesMapper bookCategoriesMapper;
    @Autowired
    private BookDetailMapper bookDetailMapper;
    @Override
    public List<BookCategoriesVO> getAllBookCategoriesVO() {
        //实体类转VO
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("level",1);
        List<BookCategories> bookCategoriesList=bookCategoriesMapper.selectList(wrapper);
        //VO
        List<BookCategoriesVO> bookCategoriesVOList=new ArrayList<>();
        for (BookCategories bookCategories : bookCategoriesList) {

            BookCategoriesVO bookCategoriesVO=new BookCategoriesVO();
            //直接赋值
            BeanUtils.copyProperties(bookCategories,bookCategoriesVO);
//            bookCategoriesVO.setId(bookCategories.getId());
//            bookCategoriesVO.setName(bookCategories.getName());
            bookCategoriesVOList.add(bookCategoriesVO);
        }
//        List<BookCategoriesVO> bookCategoriesVOList=bookCategoriesList.stream()
//                    .map(e -> new BookCategoriesVO(
//                    e.getId(),
//                    e.getName()
//                    )).collect(Collectors.toList());
        for (BookCategoriesVO bookCategoriesVO : bookCategoriesVOList) {
            wrapper=new QueryWrapper();
            wrapper.eq("level",2);
            wrapper.eq("parent_id",bookCategoriesVO.getId());

            List<BookCategories> levelTwo=bookCategoriesMapper.selectList(wrapper);
//            List<BookCategoriesVO> levelTwoVO=new ArrayList<>();
//            for (BookCategories bookCategories : levelTwo) {
//                BookCategoriesVO bookCategoriesVO1 =new BookCategoriesVO();
//                //直接赋值
//                bookCategoriesVO1.setId(bookCategories.getId());
//                bookCategoriesVO1.setName(bookCategories.getName());
//                bookCategoriesVOList.add(bookCategoriesVO1);
//            }
            List<BookCategoriesVO> levelTwoVO=levelTwo.stream()
                    .map(e -> new BookCategoriesVO(
                            e.getId(),
                            e.getName()
                    )).collect(Collectors.toList());
            bookCategoriesVO.setChildren(levelTwoVO);
        }
        for (int i = 0; i < bookCategoriesVOList.size(); i++) {
            //图片赋值
            bookCategoriesVOList.get(i).setBannerImg("/images/banner"+i+".jpg");
            wrapper=new QueryWrapper();
            wrapper.eq("category_one",bookCategoriesVOList.get(i).getId());
            //根据已卖数量倒序查看
            wrapper.last("limit 0,5");
            wrapper.orderByDesc("sell_count");
            List<BookDetail> bookDetailList=bookDetailMapper.selectList(wrapper);
            List<BookVO> bookVOList=bookDetailList.stream()
                    .map(e ->new BookVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )).collect(Collectors.toList());
            bookCategoriesVOList.get(i).setBookVOList(bookVOList);
        }
        return bookCategoriesVOList;
    }
}
