package ru.otus.spring.services;

import java.util.List;

public interface AuthorService {

    String save(String name, String surname);

    List<String> findAll();

    String findById(long id);

    String findByNameAndSurname(String name, String surname);

    String updateById(long id, String name, String surname);

    String deleteById(long id);
}
