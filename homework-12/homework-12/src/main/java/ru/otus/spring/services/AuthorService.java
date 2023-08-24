package ru.otus.spring.services;

import ru.otus.spring.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void save(String name, String surname);

    List<Author> findAll();

    String findById(String id);

    Optional<Author> findByNameAndSurname(String name, String surname);

    void updateById(String id, String name, String surname);

    String deleteById(String id);
}
