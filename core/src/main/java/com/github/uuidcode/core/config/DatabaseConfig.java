package com.github.uuidcode.core.config;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.mysql.jdbc.Driver;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        String jdbcParameter = Stream.<String>builder()
            .add("autoReconnect=true")
            .add("useUnicode=true")
            .add("characterEncoding=utf8")
            .add("mysqlEncoding=utf8")
            .build()
            .collect(Collectors.joining("&"));

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:p6spy:mysql://127.0.0.1:3306/prototype1?" + jdbcParameter);
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setDriverClassName(Driver.class.getName());
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}