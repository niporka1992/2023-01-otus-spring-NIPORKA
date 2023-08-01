package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.entities.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    Flux<Book> findBookByName(String name);
}
