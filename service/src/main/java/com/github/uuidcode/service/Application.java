package com.github.uuidcode.service;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.uuidcode.Entry;

@SpringBootApplication(scanBasePackageClasses = Entry.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
