package ru.otus.spring.repositories;

import ru.otus.spring.entities.Comment;
import ru.otus.spring.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment insert(Comment comment);

    void update(Comment comment) throws EntityNotFoundException;

    Optional<Comment> getById(long id);

    void deleteById(long id) throws EntityNotFoundException;

    List<Comment> getCommentsByBookId(long bookId);
}
