package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    Genre insert(Genre genre);

    Genre updateById(long id, String name);

    Genre getById(long id);

    Genre getByName(String name);

    List<Genre> getAll();

    long deleteById(long id);
}
