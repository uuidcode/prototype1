package com.github.uuidcode.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.core.dao.BookDao;
import com.github.uuidcode.core.entity.Author;
import com.github.uuidcode.core.entity.Book;
import com.github.uuidcode.core.entity.QBook;
import com.github.uuidcode.core.util.CoreUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.github.uuidcode.core.entity.QAuthor.author;
import static com.github.uuidcode.core.entity.QBook.book;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

@Transactional
@Service
public class BookService extends SimpleJpaRepository<Book, Long> {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorService authorService;

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

    public Book selectById(Long bookId) {
        List<Tuple> tupleList = this.queryFactory
            .select(book, author)
            .from(book)
            .leftJoin(author).on(author.bookId.eq(book.bookId))
            .where(book.bookId.eq(bookId))
            .fetch();

        if (CoreUtil.isEmpty(tupleList)) {
            return null;
        }

        List<Author> authorList = tupleList.stream()
            .map(tuple -> tuple.get(author))
            .collect(toList());

        return tupleList.get(0).get(book)
            .setAuthorList(authorList);
    }

    public Book setAuthorList(Book book) {
        List<Author> authorList = this.authorService.findByBookId(book.getBookId());
        return book.setAuthorList(authorList);
    }

    public Book selectById2(Long bookId) {
        return this.findById(bookId)
            .map(this::setAuthorList)
            .orElse(null);
    }
}
