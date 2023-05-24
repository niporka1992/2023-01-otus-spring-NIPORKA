package ru.otus.spring.services;

public interface CommentService {
    String save(String text, long booId);

    String findById(long id);

    String updateById(long id, String text);

    String deleteById(long id);
}
