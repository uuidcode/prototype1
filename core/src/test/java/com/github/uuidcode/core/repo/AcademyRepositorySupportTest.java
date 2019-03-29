package com.github.uuidcode.core.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.uuidcode.core.entity.Academy;
import com.github.uuidcode.core.util.CoreUtil;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class AcademyRepositorySupportTest {
    @Autowired
    private AcademyRepo academyRepo;

    @Test
    public void test() {
        this.academyRepo.save(Academy.of().setName("1").setAddress("2"));
        List<Academy> list = this.academyRepo.findAll();
        String json = CoreUtil.toJson(list);

        System.out.println(json);
    }
}