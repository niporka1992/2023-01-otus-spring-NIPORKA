package ru.otus.spring.repositories;

import ru.otus.spring.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author insert(Author author);

    void updateById(long id, Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void deleteById(long id);

    List<Author> getByNameAndSurname(String name, String surname);
}




