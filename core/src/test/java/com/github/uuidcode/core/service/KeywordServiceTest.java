package com.github.uuidcode.core.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.uuidcode.core.entity.Keyword;

class KeywordServiceTest extends CoreTest {
    @Autowired
    private KeywordService keywordService;

    @Test
    public void save() {
        List<Keyword> keywordList = new ArrayList<>();
        keywordList.add(Keyword.of().setBookId(1L).setName("test1"));
        keywordList.add(Keyword.of().setBookId(1L).setName("test2"));

        this.keywordService.saveAll(keywordList);
    }
}