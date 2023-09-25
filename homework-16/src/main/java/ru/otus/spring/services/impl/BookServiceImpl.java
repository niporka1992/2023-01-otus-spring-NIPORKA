package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.services.BookService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public void save(Book b) {
        bookRepository.save(b);
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }


    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Long count() {
        return bookRepository.count();
    }
}
