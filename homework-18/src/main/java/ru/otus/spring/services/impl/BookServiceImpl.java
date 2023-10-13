package ru.otus.spring.services.impl;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
    @Retry(name = "instanceA")
    @RateLimiter(name = "instanceA")
    @Bulkhead(name = "backendA")
    public void save(Book b) {
        bookRepository.save(b);
    }


    @Override
    @CircuitBreaker(name = "instanceA")
    @Retry(name = "instanceA")
    @RateLimiter(name = "instanceA")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @SneakyThrows
    @Override
    @CircuitBreaker(name = "instanceA", fallbackMethod = "fallBack")
    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }


    @Override
    @CircuitBreaker(name = "instanceA")
    @Retry(name = "instanceA")
    public void deleteById(String id) {
        bookRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Long count() {
        return bookRepository.count();
    }

    public Optional<Book> fallBack(String parameter, Throwable throwable) {
        return Optional.of(new Book("N/A"));
    }
}
