package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    long count();

    Author insert(Author author);

    Author updateById(long id, String name, String surname);

    Author getById(long id);

    List<Author> getAll();

    long deleteById(long id);

    Author getByNameAndSurname(String name, String surname);
}





