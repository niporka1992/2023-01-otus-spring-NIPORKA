package ru.otus.spring.repositories;

import ru.otus.spring.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book insert(Book book);

    boolean updateById(long id, Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    boolean deleteById(long id);
}
