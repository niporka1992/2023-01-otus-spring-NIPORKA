package ru.otus.spring.repositories;

import ru.otus.spring.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment insert(Comment comment);

    boolean updateById(long id, String text);

    Optional<Comment> getById(long id);

    List<Comment> getByText(String text);

    List<Comment> getAll();

    boolean deleteById(long id);
}
