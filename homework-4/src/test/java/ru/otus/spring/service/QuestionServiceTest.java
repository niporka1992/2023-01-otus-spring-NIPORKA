package ru.otus.spring.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.AnswerDaoCsvFile;
import ru.otus.spring.dao.QuestionDaoCsvFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionServiceTest {
    Resource resourceRU = new FileSystemResource("src/main/resources/questions_and_answers_RU.csv");
    Resource resourceEN = new FileSystemResource("src/main/resources/questions_and_answers_EN.csv");

    AnswerDao answerDaoRU = new AnswerDaoCsvFile(resourceRU);
    AnswerDao answerDaoEN = new AnswerDaoCsvFile(resourceEN);

    QuestionDaoCsvFile questionDaoCsvFileRU = new QuestionDaoCsvFile(resourceRU);
    QuestionDaoCsvFile questionDaoCsvFileEN = new QuestionDaoCsvFile(resourceEN);


    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_RU.csv")
    void runGetQuestionsRU(Integer id, String question) throws IOException {
        assertEquals(questionDaoCsvFileRU.findAllQuestions().get(--id).getName(), question);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_RU.csv")
    void runGetAnswersRU(Integer id, String question, String answer) throws IOException {
        assertEquals(answerDaoRU.findAllAnswers().get(--id).getName(), answer);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_EN.csv")
    void runGetQuestionsEN(Integer id, String question) throws IOException {
        assertEquals(questionDaoCsvFileEN.findAllQuestions().get(--id).getName(), question);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/questions_and_answers_EN.csv")
    void runGetAnswersEN(Integer id, String question, String answer) throws IOException {
        assertEquals(answerDaoEN.findAllAnswers().get(--id).getName(), answer);
    }
}