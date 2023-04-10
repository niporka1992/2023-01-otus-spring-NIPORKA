package ru.otus.spring.domain;

public record Genre(long id, String name) {
    public Genre(String name) {
        this(0, name);
    }

    public Genre(long id) {
        this(id, null);
    }
}
