package ru.otus.spring.domain;

import lombok.Data;

@Data
public class TestingResult {
    private final Student student;
    private final int totalQuestions;
    private final int score;

}
