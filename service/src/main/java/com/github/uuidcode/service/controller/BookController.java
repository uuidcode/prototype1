package com.github.uuidcode.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.uuidcode.core.entity.Book;
import com.github.uuidcode.core.entity.Payload;
import com.github.uuidcode.core.entity.QBook;
import com.github.uuidcode.core.service.BookService;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String index(Model model, Pageable pageable) {
        BooleanExpression booleanExpression = QBook.book.bookId.isNotNull();
        Page<Book> pageBook = this.bookService.findAll(booleanExpression, pageable);
        Payload payload = Payload.of().setBookList(pageBook.getContent());
        model.addAttribute("payload", payload);
        return "book/index";
    }
}
