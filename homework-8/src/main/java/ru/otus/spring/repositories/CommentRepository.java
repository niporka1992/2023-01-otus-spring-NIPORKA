package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {
    Long countCommentByBook(Book book);

    void deleteAllByBook(Book book);

    List<Comment> findByBookId(String bookId);
}
