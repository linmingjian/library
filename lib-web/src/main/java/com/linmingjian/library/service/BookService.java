package com.linmingjian.library.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.linmingjian.library.base.BasePage;
import com.linmingjian.library.bean.dto.BookDto;
import com.linmingjian.library.bean.entity.Book;
import com.linmingjian.library.bean.vo.BookDetailVo;
import com.linmingjian.library.bean.vo.BookListVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface BookService extends IService<Book> {
    BasePage<BookListVo> getBookByPage(String keyword, Integer pageNum, Integer pageSize);

    BookDetailVo getBook(Long bookId);

    void insertBook(BookDto bookDto);

    int deleteBook(Long bookId);

    int updateBook(Long bookId, BookDto bookDto);
}
