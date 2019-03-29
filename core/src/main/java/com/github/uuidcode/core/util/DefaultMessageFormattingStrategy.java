package com.github.uuidcode.core.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.uuidcode.core.Application;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class DefaultMessageFormattingStrategy implements MessageFormattingStrategy {
    protected static Logger logger = LoggerFactory.getLogger(DefaultMessageFormattingStrategy.class);

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql) {
        String databaseUrl = null;
        try {
            databaseUrl = DefaultLoggingEventListener.local
                .get()
                .getConnection()
                .getMetaData()
                .getURL();
        } catch (Throwable t) {
            if (logger.isErrorEnabled()) {
                this.logger.error("error PrettySqlFormat formatMessage", t);
            }
        }

        StackTraceElement stackTraceElement = this.getDatabaseStackTraceElement();
        String formattedSQL = CoreUtil.getFormattedSQL(sql);

        return Stream.<String>builder()
            .add(this.stackTraceElementToString(stackTraceElement))
            .add("databaseUrl: " + databaseUrl)
            .add("category: " + category)
            .add("elapsed: " + elapsed  + "ms")
            .add("formatted query: ")
            .add(formattedSQL)
            .build()
            .collect(Collectors.joining(System.lineSeparator()));
    }

    private StackTraceElement getDatabaseStackTraceElement() {
        Exception exception = new Exception();
        StackTraceElement[] stackTraceElementArray = exception.getStackTrace();

        return Arrays.stream(stackTraceElementArray)
            .filter(s -> s.getClassName().startsWith(Application.class.getPackage().getName()))
            .filter(s -> s.getClassName().endsWith("Service"))
            .findFirst()
            .orElse(null);
    }

    private String stackTraceElementToString(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null) {
            return null;
        }

        String className = stackTraceElement.getClassName();
        String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
        String methodName = stackTraceElement.getMethodName();
        int line = stackTraceElement.getLineNumber();

        return Stream.<String>builder()
            .add(">>> ")
            .add(className)
            .add(".")
            .add(methodName)
            .add("(")
            .add(simpleClassName)
            .add(".java")
            .add(":")
            .add(String.valueOf(line))
            .add(")")
            .build()
            .collect(Collectors.joining(""));
    }
}