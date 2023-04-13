package ru.otus.spring.service;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookService {
    long getCount();

    Book save(Book book);

    List<Book> findAll();

    Book findById(long id);

    Book updateById(long id, Book book);

    long deleteById(long id);
}
