package ru.otus.spring.dao;

import ru.otus.spring.domain.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> findAllAnswers();
}

