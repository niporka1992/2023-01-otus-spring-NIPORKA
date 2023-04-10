package ru.otus.spring.service;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    long getCount();

    long save(Author author);

    List<Author> findAll();

    Author findById(long id);

    Author findByNameAndSurname(String name, String surname);

    long updateById(long id, Author author);

    long deleteById(long id);
}
