package com.github.uuidcode.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;

@Configuration
public class HandlebarsConfig {
    @Bean
    public ViewResolver viewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("classpath:/templates");
        viewResolver.setSuffix(".hbs");
        return viewResolver;
    }
}
