package ru.otus.spring.repositories;

import ru.otus.spring.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book insert(Book book);

    void updateById(long id, Book book);

    Optional<Book> getById(long id);

    List<Book> getAllWithAuthorAndGenre();

    void deleteById(long id);
}
