package ru.otus.spring.services;

import ru.otus.spring.entities.Genre;

import java.util.List;

public interface GenreService {
    String save(String name);

    List<Genre> findAll();

    String findById(long id);

    List<Genre> findByName(String name);

    String updateById(long id, String name);

    String deleteById(long id);
}
