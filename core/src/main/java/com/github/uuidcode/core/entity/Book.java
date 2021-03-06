package com.github.uuidcode.core.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.experimental.Accessors;

@Entity
@Data(staticConstructor = "of")
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private Integer page;
    private Date createdAt;
    @Transient
    private List<Author> authorList;
    @Transient
    private List<Keyword> keywordsList;
}