package ru.otus.spring.services;

import ru.otus.spring.entities.Book;

import java.util.List;

public interface BookService {

    String save(String name, String authorId, String genreId);

    List<Book> findAll();

    String findById(String id);

    void updateById(String id, String name, String authorId, String genreId);

    void deleteById(String id);
}
