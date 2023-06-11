package ru.otus.spring.repositories;

import ru.otus.spring.entities.Author;
import ru.otus.spring.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    Author insertOrUpdate(Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    void deleteById(long id) throws EntityNotFoundException;

    List<Author> getByNameAndSurname(String name, String surname);
}





