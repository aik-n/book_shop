package com.book.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.entity.Img;
import com.book.mapper.ImgMapper;
import com.book.service.ImgService;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements ImgService {

}
