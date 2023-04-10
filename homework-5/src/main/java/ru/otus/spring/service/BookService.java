package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    long getCount();

    long save(Book book);

    List<Book> findAll();

    Book findById(long id);

    long updateById(long id, Book book);

    long deleteById(long id);
}
