package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AnswerDaoTest {
    Resource resourceRU = new FileSystemResource("src/main/resources/questions_and_answers_RU.csv");
    Resource resourceEN = new FileSystemResource("src/main/resources/questions_and_answers_EN.csv");

    @Test
    void findAllAnswerSizeRU() throws IOException {
        AnswerDaoCsvFile answerDaoCsvFile = new AnswerDaoCsvFile(resourceRU);
        assertEquals(5, answerDaoCsvFile.findAllAnswers().size());

    }

    @Test
    void findAllAnswerSizeEN() throws IOException {
        AnswerDaoCsvFile answerDaoCsvFile = new AnswerDaoCsvFile(resourceEN);
        assertEquals(5, answerDaoCsvFile.findAllAnswers().size());

    }
}