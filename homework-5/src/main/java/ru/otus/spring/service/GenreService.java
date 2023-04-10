package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    long getCount();

    long save(Genre genre);

    List<Genre> findAll();

    Genre findById(long id);

    Genre findByName(String name);

    long updateById(long id, Genre genre);

    long deleteById(long id);
}
