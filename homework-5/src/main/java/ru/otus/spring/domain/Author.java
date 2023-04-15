package ru.otus.spring.domain;

public record Author(long id, String name, String surname) {
    public Author(String name, String surname) {
        this(0, name, surname);
    }

    public Author(long id) {
        this(id, null, null);
    }
}
