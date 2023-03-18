package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDaoCsvFileTest {

    @Autowired
    private QuestionDaoCsvFile questionDaoCsvFile;

    @Test
    @DisplayName("Получает верное кол-во вопросов.")
    void findAllAnswers() {
        assertEquals(5, questionDaoCsvFile.findAllQuestions().size());
    }
}