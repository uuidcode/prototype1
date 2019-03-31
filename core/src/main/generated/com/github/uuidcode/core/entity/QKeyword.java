package com.github.uuidcode.core.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QKeyword is a Querydsl query type for Keyword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKeyword extends EntityPathBase<Keyword> {

    private static final long serialVersionUID = 1097814629L;

    public static final QKeyword keyword = new QKeyword("keyword");

    public final NumberPath<Long> bookId = createNumber("bookId", Long.class);

    public final NumberPath<Long> keywordId = createNumber("keywordId", Long.class);

    public final StringPath name = createString("name");

    public QKeyword(String variable) {
        super(Keyword.class, forVariable(variable));
    }

    public QKeyword(Path<? extends Keyword> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKeyword(PathMetadata metadata) {
        super(Keyword.class, metadata);
    }

}

