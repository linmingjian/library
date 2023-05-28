package com.linmingjian.library.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linmingjian.library.bean.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookRepository extends BaseMapper<Book> {
}
