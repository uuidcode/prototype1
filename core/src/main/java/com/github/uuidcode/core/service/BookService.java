package com.github.uuidcode.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.github.uuidcode.core.dao.BookDao;
import com.github.uuidcode.core.entity.Book;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.github.uuidcode.core.entity.QBook.book;

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
