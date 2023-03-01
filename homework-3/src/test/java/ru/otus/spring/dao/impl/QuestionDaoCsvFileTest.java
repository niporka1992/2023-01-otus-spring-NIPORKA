package ru.otus.spring.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.dao.QuestionDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDaoCsvFileTest {

    @Autowired
    QuestionDao questionDao;

    @Test
    @DisplayName("Получает верное кол-во ответов.")
    void findAllAnswers() {
        assertEquals(5, questionDao.findAllQuestions().size());
    }
}
