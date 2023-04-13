package ru.otus.spring.dao;

import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {
    long getCount();

    Book insert(Book book);

    Book updateById(long id, Book book);

    Book getById(long id);

    List<Book> getAll();

    long deleteById(long id);
}
