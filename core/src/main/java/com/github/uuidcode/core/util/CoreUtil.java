package com.github.uuidcode.core.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hibernate.engine.jdbc.internal.BasicFormatterImpl;
import org.slf4j.Logger;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.uuidcode.core.config.StringTrimModule;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static java.util.Optional.ofNullable;
import static org.slf4j.LoggerFactory.getLogger;

public class CoreUtil {
    protected static Logger logger = getLogger(CoreUtil.class);

    public static ObjectMapper objectMapper;
    public static ObjectWriter objectWriter;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(NON_NULL);
        objectMapper.setVisibility(PropertyAccessor.ALL, NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, ANY);
        objectMapper.registerModule(StringTrimModule.of());
        objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
    }

    public static <T> T parseJson(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (Throwable t) {
            if (logger.isErrorEnabled()) {
                logger.error(">>> error CoreUtil parseJson", t);
            }
        }

        return null;
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

    public static boolean isEmpty(List list) {
        return ofNullable(list)
            .map(List::isEmpty)
            .orElse(true);
    }
}
