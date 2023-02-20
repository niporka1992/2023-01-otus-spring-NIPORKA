package ru.otus.spring.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionDaoTest {
    Resource resourceRU = new FileSystemResource("src/main/resources/questions_and_answers_RU.csv");
    Resource resourceEN = new FileSystemResource("src/main/resources/questions_and_answers_EN.csv");

    @Test
    void findAllQuestionsSizeRU() throws IOException {
        QuestionDao questionDaoCsvFile = new QuestionDaoCsvFile(resourceRU);
        assertEquals(5, questionDaoCsvFile.findAllQuestions().size());

    }

    @Test
    void findAllQuestionsSizeEN() throws IOException {
        QuestionDao questionDaoCsvFile = new QuestionDaoCsvFile(resourceEN);
        assertEquals(5, questionDaoCsvFile.findAllQuestions().size());

    }
}
