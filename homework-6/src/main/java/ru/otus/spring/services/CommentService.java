package ru.otus.spring.services;

import ru.otus.spring.entities.Comment;

import java.util.List;

public interface CommentService {
    String save(String text, long booId);

    String findById(long id);

    void updateById(long id, String text);

    void deleteById(long id);

    List<Comment> findByBookId(long bookId);
}
