package com.github.uuidcode.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.uuidcode.core.util.CoreUtil;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        return CoreUtil.objectMapper;
    }

    @Bean
    public ObjectWriter objectWriter() {
        return CoreUtil.objectWriter;
    }
}
