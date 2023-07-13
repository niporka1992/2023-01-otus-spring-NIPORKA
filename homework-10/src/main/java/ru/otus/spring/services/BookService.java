package ru.otus.spring.services;

import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

 //   void save(Book book);

    List<Book> findAll();

  //  Optional<Book> findById(String id);

    List<BookDto> findByName(String name);

   // void deleteById(String id);
}
