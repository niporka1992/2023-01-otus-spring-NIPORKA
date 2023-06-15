package ru.otus.spring.services;

import ru.otus.spring.entities.Author;

import java.util.List;

public interface AuthorService {

    void save(String name, String surname);

    List<Author> findAll();

    String findById(long id);

    List<Author> findByNameAndSurname(String name, String surname);

    void updateById(long id, String name, String surname);

    void deleteById(long id);
}
