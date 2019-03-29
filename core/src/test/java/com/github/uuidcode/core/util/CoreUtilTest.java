package com.github.uuidcode.core.util;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.github.uuidcode.core.model.Project;

class CoreUtilTest {
    @Test
    public void test() {
        Project project = Project.of()
            .setId(1L)
            .setCreateAt(new Date());

        String json = CoreUtil.toJson(project);

        System.out.println(json);
    }
}