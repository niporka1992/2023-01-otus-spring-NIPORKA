package ru.otus.spring.services;

import ru.otus.spring.entities.Genre;

import java.util.List;

public interface GenreService {
    void save(String name);

    List<Genre> findAll();

    Genre findById(String id);

    Genre findByName(String name);

    void updateById(String id, String name);

    String deleteById(String id);
}
