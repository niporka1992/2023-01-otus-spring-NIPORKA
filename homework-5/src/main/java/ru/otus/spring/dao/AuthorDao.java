package ru.otus.spring.dao;

import ru.otus.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    long count();

    long insert(Author author);

    long updateById(long id, Author author);

    Author getById(long id);

    List<Author> getAll();

    long deleteById(long id);

    Author getByNameAndSurname(String name, String surname);
}





