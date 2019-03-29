package com.github.uuidcode.core.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.core.entity.Author;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.github.uuidcode.core.entity.QAuthor.author;

@Transactional
@Service
public class AuthorService extends SimpleJpaRepository<Author, Long> {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    public AuthorService(EntityManager entityManager) {
        super(Author.class, entityManager);
    }

    public List<Author> findByBookId(Long bookId) {
        return this.queryFactory.selectFrom(author)
            .where(author.bookId.eq(bookId))
            .fetch();
    }
}
