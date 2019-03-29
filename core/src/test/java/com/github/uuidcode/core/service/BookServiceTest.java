package com.github.uuidcode.core.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.uuidcode.core.entity.Author;
import com.github.uuidcode.core.entity.Book;
import com.github.uuidcode.core.util.CoreUtil;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BookServiceTest {
    protected static Logger logger = getLogger(BookServiceTest.class);

    @Autowired
    private BookService bookService;

    @Test
    public void save() {
        Book book = Book.of().setTitle("1").setPage(2).setCreatedAt(new Date());
        this.bookService.save(book);

        assertThat(book.getBookId()).isNotNull();
        assertThat(book.getTitle()).isEqualTo("1");
        assertThat(book.getCreatedAt()).isAfterOrEqualsTo(book.getCreatedAt());
    }

    @Test
    public void transactionSuccess() {
        this.transaction(() -> this.bookService.transactionSuccess(), 2);
    }

    @Test
    public void transactionFail() {
        this.transaction(() -> this.bookService.transactionFail(), 0);
    }

    public void transaction(Runnable runnable, int saveCount) {
        long count = this.bookService.count();

        try {
            runnable.run();
        } catch (Throwable t) {
            if (logger.isErrorEnabled()) {
                logger.error(">>> transaction error", t);
            }
        }

        assertThat(this.bookService.count()).isEqualTo(count + saveCount);
    }

    @Test
    public void findAll() {
        List<Book> list = this.bookService.findAll();

        assertThat(list.size()).isGreaterThan(0);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> findAll list: {}", CoreUtil.toJson(list));
        }
    }

    @Test
    public void findAll2() {
        List<Book> list = this.bookService.findAll2();

        assertThat(list.size()).isGreaterThan(0);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> findAll list: {}", CoreUtil.toJson(list));
        }
    }

    @Test
    public void findByTitle() {
        List<Book> list = this.bookService.findByTitle("1");

        assertThat(list.size()).isGreaterThan(0);

        int size = list.stream()
            .filter(CoreUtil.equals(Book::getTitle, "1"))
            .collect(Collectors.toList())
            .size();

        assertThat(list.size()).isEqualTo(size);
    }

    @Test
    public void saveAuthor() {
        Author author = Author.of().setBookId(1L).setName("hello");

    }

    @Test
    public void selectById() {
        Book book = this.bookService.selectById(1L);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> selectById book: {}", CoreUtil.toJson(book));
        }
    }

    @Test
    public void selectById2() {
        Book book = this.bookService.selectById2(1L);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> selectById2 book: {}", CoreUtil.toJson(book));
        }
    }
}