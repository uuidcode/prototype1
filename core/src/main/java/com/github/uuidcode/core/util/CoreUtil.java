package com.github.uuidcode.core.util;

import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.Optional.ofNullable;

public class CoreUtil {
    public static ObjectMapper objectMapper;
    public static ObjectWriter objectWriter;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.setVisibility(PropertyAccessor.ALL, NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, ANY);
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }


    public static String toJson(Object object) {
        return unchecked(objectWriter::writeValueAsString).apply(object);
    }

    public static <T, R> Function<T, R> unchecked(CheckedFunction<T, R> mapper) {
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Throwable throwable) {
                throw new RuntimeException(throwable);
            }
        };
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static String getFormattedSQL(String sql) {
        return new BasicFormatterImpl().format(sql);
    }

    public static <T> Predicate<T> equals(Function<T, String> mapper, String value) {
        return t -> ofNullable(t)
            .map(mapper)
            .filter(text -> Objects.equals(text, value))
            .isPresent();
    }
}
