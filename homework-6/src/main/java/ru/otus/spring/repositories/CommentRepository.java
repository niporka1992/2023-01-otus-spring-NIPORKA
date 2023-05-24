package ru.otus.spring.repositories;

import ru.otus.spring.entities.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment insert(Comment comment);

    void updateById(long id, String text);

    Optional<Comment> getById(long id);

    void deleteById(long id);
}
