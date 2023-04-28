package ru.otus.spring.services;

import java.util.List;

public interface CommentService {

    String save(String text, long booId);

    List<String> findAll();

    String findById(long id);

    List<String> findByText(String text);

    String updateById(long id, String text);

    String deleteById(long id);
}
