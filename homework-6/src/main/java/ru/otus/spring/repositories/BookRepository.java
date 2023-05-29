package ru.otus.spring.repositories;

import ru.otus.spring.entities.Book;
import ru.otus.spring.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book insert(Book book);

    void update(Book book) throws EntityNotFoundException;

    Optional<Book> getById(long id);

    List<Book> getAllWithAuthorAndGenre();

    void deleteById(long id) throws EntityNotFoundException;
}
