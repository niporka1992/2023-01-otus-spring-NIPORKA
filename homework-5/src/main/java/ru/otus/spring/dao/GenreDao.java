package ru.otus.spring.dao;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreDao {
    long count();

    long insert(Genre genre);

    long updateById(long id, Genre genre);

    Genre getById(long id);

    Genre getByName(String name);

    List<Genre> getAll();

    long deleteById(long id);
}
