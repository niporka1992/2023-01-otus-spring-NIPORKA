package ru.otus.spring.services;

import ru.otus.spring.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void save(Book book);

    List<Book> findAll();

    Optional<Book> findById(String id);

    void deleteById(String id);

    Long count();
}
