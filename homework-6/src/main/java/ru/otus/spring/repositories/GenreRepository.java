package ru.otus.spring.repositories;

import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre insert(Genre genre);

    void updateById(long id, String name) throws EntityNotFoundException;

    Optional<Genre> getById(long id);

    List<Genre> getByName(String name);

    List<Genre> getAll();

    void deleteById(long id) throws EntityNotFoundException;
}
