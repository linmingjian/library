package com.linmingjian.library.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmingjian.library.base.BasePage;
import com.linmingjian.library.bean.dto.BookDto;
import com.linmingjian.library.bean.entity.Book;
import com.linmingjian.library.bean.entity.User;
import com.linmingjian.library.bean.vo.BookDetailVo;
import com.linmingjian.library.bean.vo.BookListVo;
import com.linmingjian.library.repository.BookRepository;
import com.linmingjian.library.service.BookService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends ServiceImpl<BookRepository, Book> implements BookService {

    public void insertBook(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setCode(bookDto.getCode());
        book.setDescription(bookDto.getDescription());
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        baseMapper.insert(book);
    }

    @Override
    public BasePage<BookListVo> getBookByPage(String keyword, Integer pageNum, Integer pageSize) {
        // 分页
        Page<Book> page = new Page<>(pageNum, pageSize);
        // 条件构造器
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        // 如果存在关键字
        if (StrUtil.isNotBlank(keyword)) {
            queryWrapper.like(Book::getName, keyword);
        }

        // 分页查询
        Page<Book> bookPage = baseMapper.selectPage(page, queryWrapper);
        // 构造返回对象
        List<BookListVo> bookListVoList = bookPage.getRecords()
                .stream()
                .map(book -> new BookListVo().setName(book.getName())
                        .setId(book.getId())
                        .setDescription(book.getDescription())
                        .setAuthor(book.getAuthor())
                        .setName(book.getName()))
                .collect(Collectors.toList());
        return new BasePage<>(
                bookPage.getCurrent(),
                bookPage.getSize(),
                bookPage.getPages(),
                bookPage.getTotal(),
                bookListVoList);
    }

    @Cacheable(value = "book", key = "#bookId")
    public BookDetailVo getBook(Long bookId) {
        Book book = baseMapper.selectById(bookId);
        BookDetailVo bookDetailVo = new BookDetailVo();
        bookDetailVo.setAuthor(book.getAuthor())
                .setCode(book.getCode())
                .setId(book.getId())
                .setPrice(book.getPrice())
                .setDescription(book.getDescription());
        return bookDetailVo;
    }

    @CachePut(value = "book", key = "#bookId")
    public int updateBook(Long bookId, BookDto bookDto) {
        Book book = new Book();
        book.setId(bookId);
        book.setName(bookDto.getName());
        book.setCode(bookDto.getCode());
        book.setPrice(bookDto.getPrice());
        book.setAuthor(bookDto.getAuthor());
        book.setDescription(bookDto.getDescription());
        book.setUpdateTime(new Date());
        return baseMapper.updateById(book);
    }

    @CacheEvict(value = "book", key = "#bookId")
    public int deleteBook(Long bookId) {
        return baseMapper.deleteById(bookId);
    }
}
