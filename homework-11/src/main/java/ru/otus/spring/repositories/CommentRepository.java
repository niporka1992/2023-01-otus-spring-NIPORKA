package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
    Mono<Comment> deleteAllByBook(Book book);
}
