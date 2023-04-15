package ru.otus.spring.domain;

public record Book(long id, String name, Author author, Genre genre) {
    public Book(String name, Author author, Genre genre) {
        this(0, name, author, genre);
    }
}
