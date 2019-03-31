package com.github.uuidcode.core.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.core.entity.Keyword;

@Transactional
@Service
public class KeywordService extends QuerydslJpaRepository<Keyword, Long> {
    @Autowired
    public KeywordService(EntityManager entityManager) {
        super(Keyword.class, entityManager);
    }
}
