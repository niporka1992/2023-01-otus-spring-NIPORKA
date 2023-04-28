package ru.otus.spring.repositories;

import ru.otus.spring.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author insert(Author author);

    boolean updateById(long id, String name, String surname);

    Optional<Author> getById(long id);

    List<Author> getAll();

    boolean deleteById(long id);

    List<Author> getByNameAndSurname(String name, String surname);
}





