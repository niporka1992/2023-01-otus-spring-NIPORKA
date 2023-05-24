package ru.otus.spring.repositories;

import ru.otus.spring.entities.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> insert(Genre genre);

    void updateById(long id, String name);

    Optional<Genre> getById(long id);

    List<Genre> getByName(String name);

    List<Genre> getAll();

    void deleteById(long id);
}
