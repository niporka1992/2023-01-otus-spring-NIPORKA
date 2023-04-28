package ru.otus.spring.services;

import java.util.List;

public interface GenreService {

    String save(String name);

    List<String> findAll();

    String findById(long id);

    String findByName(String name);

    String updateById(long id, String name);

    String deleteById(long id);
}
