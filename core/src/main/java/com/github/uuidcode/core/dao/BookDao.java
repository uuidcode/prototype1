package com.github.uuidcode.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.github.uuidcode.core.entity.Book;

@Mapper
public interface BookDao {
    List<Book> findAll2();
}
