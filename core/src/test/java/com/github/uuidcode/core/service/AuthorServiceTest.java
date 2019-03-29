package com.github.uuidcode.core.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.core.entity.Author;

class AuthorServiceTest extends CoreTest {
    @Autowired
    private AuthorService authorService;

    @Test
    public void save() {
        Author author = Author.of().setBookId(1L).setName("hello2");
        this.authorService.save(author);
    }
}