package ru.otus.spring.service;

import ru.otus.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    long getCount();

    Genre save(Genre genre);

    List<Genre> findAll();

    Genre findById(long id);

    Genre findByName(String name);

    Genre updateById(long id, String name);

    long deleteById(long id);
}
