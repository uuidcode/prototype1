package com.github.uuidcode.core.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

public class Relation {
    private Set<Type> relationSet = new HashSet<>();

    public enum Type {
        AUTHOR_LIST,
        KEYWORD_LIST
    }

    public static Relation of() {
        return new Relation();
    }

    public Relation add(Type type) {
        this.relationSet.add(type);
        return this;
    }

    private static boolean hasType(Relation relation, Type type) {
        return ofNullable(relation)
            .filter(Relation.hasType(type))
            .isPresent();
    }

    private static Predicate<Relation> hasType(Type type) {
        return relation -> relation.relationSet.contains(type);
    }

    public static boolean hasKeywordList(Relation relation) {
        return hasType(relation, Type.KEYWORD_LIST);
    }

    public static boolean hasAuthorList(Relation relation) {
        return hasType(relation, Type.AUTHOR_LIST);
    }
}
