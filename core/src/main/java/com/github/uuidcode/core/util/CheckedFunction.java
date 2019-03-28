package com.github.uuidcode.core.util;

public interface CheckedFunction<T, R> {
    R apply(T t) throws Throwable;
}
