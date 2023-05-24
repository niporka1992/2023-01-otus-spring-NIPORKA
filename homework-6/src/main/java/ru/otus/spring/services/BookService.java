package ru.otus.spring.services;

import ru.otus.spring.entities.Book;

import java.util.List;

public interface BookService {

    String save(String name, long authorId, long genreId);

    List<Book> findAll();

    String findById(long id);

    String updateById(long id, Book book);

    String deleteById(long id);
}
