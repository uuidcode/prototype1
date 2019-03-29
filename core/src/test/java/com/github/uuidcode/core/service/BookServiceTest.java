package com.github.uuidcode.core.service;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.uuidcode.core.entity.Book;
import com.github.uuidcode.core.util.CoreUtil;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    protected static Logger logger = getLogger(BookServiceTest.class);

    @Autowired
    private BookService bookService;

    @Test
    public void save() {
        Book book = Book.of().setTitle("1").setPage(2).setCreateAt(new Date());
        this.bookService.save(book);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> save book: {}", CoreUtil.toJson(book));
        }
    }

    @Test
    public void findAll() {
        List<Book> list = this.bookService.findAll();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> findAll list: {}", CoreUtil.toJson(list));
        }
    }

    @Test
    public void findAll2() {
        List<Book> list = this.bookService.findAll2();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> findAll list: {}", CoreUtil.toJson(list));
        }
    }

    @Test
    public void findByTitle() {
        List<Book> list = this.bookService.findByTitle("1");

        if (logger.isDebugEnabled()) {
            logger.debug(">>> findByTitle list: {}", CoreUtil.toJson(list));
        }
    }
}