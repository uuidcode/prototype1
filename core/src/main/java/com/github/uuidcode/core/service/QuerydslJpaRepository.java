package com.github.uuidcode.core.service;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import lombok.experimental.Delegate;

public class QuerydslJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {
    @Delegate
    private QuerydslJpaPredicateExecutor<T> querydslJpaPredicateExecutor;

    public QuerydslJpaRepository(Class<T> tClass, EntityManager entityManager) {
        super(tClass, entityManager);

        JpaEntityInformation<T, Long> information =
            new JpaMetamodelEntityInformation<>(tClass, entityManager.getMetamodel());

        this.querydslJpaPredicateExecutor = new QuerydslJpaPredicateExecutor<>(information,
            entityManager, SimpleEntityPathResolver.INSTANCE, null);
    }
}
