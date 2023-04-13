package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    long getCount();

    Author save(Author author);

    List<Author> findAll();

    Author findById(long id);

    Author findByNameAndSurname(String name, String surname);

    Author updateById(long id, String name, String surname);

    long deleteById(long id);
}
