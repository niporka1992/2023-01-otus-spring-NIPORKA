package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.services.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDto> findByName(String name) {
        return bookRepository.findBookByName(name).stream().map(BookDto::toBookDto).toList();
    }

  /*  @Override
    public void save(Book b) {
        bookRepository.save(b);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }
*/
}
