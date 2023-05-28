package com.linmingjian.library.controller;

import com.linmingjian.library.base.BasePage;
import com.linmingjian.library.bean.dto.BookDto;
import com.linmingjian.library.bean.vo.BookDetailVo;
import com.linmingjian.library.bean.vo.BookListVo;
import com.linmingjian.library.exception.BadRequestException;
import com.linmingjian.library.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "业务：图书接口")
@RestController
@RequestMapping("sys")
public class BookController {
    @Autowired
    private BookService bookService;

    @ApiOperation("获取图书列表")
    @GetMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@lib.check('book:list')")
    public ResponseEntity<BasePage<BookListVo>> getBookList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        BasePage<BookListVo> bookListVoPage = bookService.getBookByPage(keyword, pageNum, pageSize);
        return new ResponseEntity<>(bookListVoPage, HttpStatus.OK);
    }

    @ApiOperation("获取图书")
    @GetMapping(value = "books/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@lib.check('book:read')")
    public ResponseEntity<BookDetailVo> getBook(@ApiParam("图书ID") @PathVariable("bookId") Long bookId) {
        BookDetailVo bookDetailVo = bookService.getBook(bookId);
        return new ResponseEntity<>(bookDetailVo, HttpStatus.OK);
    }

    @ApiOperation("新增图书")
    @PostMapping(value = "books", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@lib.check('book:insert')")
    public ResponseEntity<Void> insertBook(@ApiParam("图书") @RequestBody @Validated BookDto bookDto) {
        bookService.insertBook(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("删除图书")
    @DeleteMapping(value = "book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@lib.check('book:delete')")
    public ResponseEntity<Void> deleteBook(@ApiParam("图书ID") @PathVariable("bookId") Long bookId) {
        int count = bookService.deleteBook(bookId);
        if (count == 0) {
            throw new BadRequestException("当前图书已不存在，请勿重复删除");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("更新图书")
    @PutMapping(value = "book/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("@lib.check('book:update')")
    public ResponseEntity<Void> updateBook(@ApiParam("图书ID") @PathVariable("bookId") Long bookId,
                                           @ApiParam("图书") @RequestBody @Validated BookDto bookDto) {
        int count = bookService.updateBook(bookId, bookDto);
        if (count == 0) {
            throw new BadRequestException("当前图书已不存在，请确认！");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
