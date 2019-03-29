package com.github.uuidcode.core.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import com.github.uuidcode.core.entity.Academy;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.github.uuidcode.core.entity.QAcademy.academy;

@Repository
public class AcademyRepo extends SimpleJpaRepository<Academy, Long> {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    public AcademyRepo(EntityManager entityManager) {
        super(Academy.class, entityManager);
    }

    public List<Academy> findByName(String name) {
        return queryFactory
            .selectFrom(academy)
            .where(academy.name.eq(name))
            .fetch();
    }
}
