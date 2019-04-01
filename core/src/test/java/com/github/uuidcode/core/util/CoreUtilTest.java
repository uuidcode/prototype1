package com.github.uuidcode.core.util;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import com.github.uuidcode.core.model.Project;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

class CoreUtilTest {
    protected static Logger logger = getLogger(CoreUtilTest.class);

    @Test
    public void test() {
        Project project = Project.of()
            .setId(1L)
            .setName("  abc ")
            .setCreateAt(new Date());

        String json = CoreUtil.toJson(project);

        if (logger.isDebugEnabled()) {
            logger.debug(">>> test json: {}", json);
        }


        json = CoreUtil.toJson(Project.of().setName("   "));

        if (logger.isDebugEnabled()) {
            logger.debug(">>> test json: {}", json);
        }

        project = CoreUtil.parseJson(json, Project.class);
        assertThat(project.getName()).isNull();
    }

    @Test
    public void parse() {
        Project project = CoreUtil.parseJson("{\"name\" : \" abc \"}", Project.class);
        assertThat(project.getName()).isEqualTo("abc");
    }
}