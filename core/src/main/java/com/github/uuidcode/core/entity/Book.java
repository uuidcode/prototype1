package com.github.uuidcode.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private Integer page;
    private Date createdAt;
}