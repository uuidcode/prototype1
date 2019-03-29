package com.github.uuidcode.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.core.dao.BookDao;
import com.github.uuidcode.core.entity.Book;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.github.uuidcode.core.entity.QBook.book;

@Transactional
@Service
public class BookService extends SimpleJpaRepository<Book, Long> {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private BookDao bookDao;

    @Autowired
    public BookService(EntityManager entityManager) {
        super(Book.class, entityManager);
    }

    public void saveSuccess(Book book) {
        this.bookDao.saveSuccess(book);
    }

    public void saveFail(Book book) {
        this.bookDao.saveFail(book);
    }

    public void transactionSuccess() {
        Book book = Book.of().setTitle("1").setPage(2);
        this.save(book);
        this.saveSuccess(book);
    }

    public void transactionFail() {
        Book book = Book.of().setTitle("1").setPage(2);
        this.save(book);
        this.saveFail(book);
    }

    public List<Book> findAll2() {
        List<Book> list = new ArrayList<>();
        list.addAll(this.bookDao.findAll2());
        list.addAll(this.findAll());
        return list;
    }

    public List<Book> findByTitle(String title) {
        return this.queryFactory
            .selectFrom(book)
            .where(book.title.eq(title))
            .fetch();
    }
}
