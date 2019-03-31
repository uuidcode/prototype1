package com.github.uuidcode.core.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.core.entity.Author;

@Transactional
@Service
public class AuthorService extends QuerydslJpaRepository<Author, Long> {
    @Autowired
    public AuthorService(EntityManager entityManager) {
        super(Author.class, entityManager);
    }
}
