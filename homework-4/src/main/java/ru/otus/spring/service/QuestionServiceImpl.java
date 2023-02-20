package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.Main;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.PlayerDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.service.ReaderWriter.ReaderWriterImpl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final ReaderWriterImpl readerWriter = new ReaderWriterImpl();
    private int countRightAnswer = 0;
    private final PlayerDao playerDao;

    private final MessageSource messageSource;

    public QuestionServiceImpl(QuestionDao questionDao, AnswerDao answerDao, PlayerDao playerDao, MessageSource messageSource) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        this.playerDao = playerDao;
        this.messageSource = messageSource;
    }

    public void run() {
        askName();
        testing();
        showResult();
    }

    public void testing() {
        List<Answer> answerList;
        try {
            answerList = answerDao.findAllAnswers();
            List<Answer> finalAnswerList = answerList;
            questionDao.findAllQuestions().forEach(question -> {
                        readerWriter.writeToConsole(question.getName());
                        Answer answer = new Answer(readerWriter.read());
                        if (finalAnswerList.contains(answer)) {
                            finalAnswerList.remove(answer);
                            countRightAnswer++;
                        }
                    }
            );
        } catch (IOException exception) {
            LOGGER.error("File is not available");
        }
    }

    @Override
    public void askName() {
        System.out.println(messageSource.getMessage("string.name", null, Locale.getDefault()));
        playerDao.setName();
    }

    @Override
    public void showResult() {

        try {
            System.out.printf("%s  %s %d %s  %d  %s %n",
                    playerDao.getName(),
                    messageSource.getMessage("string.correct.answers", null, Locale.getDefault()),
                    countRightAnswer,
                    messageSource.getMessage("string.from", null, Locale.getDefault()),
                    questionDao.findAllQuestions().size(),
                    messageSource.getMessage("string.questions", null, Locale.getDefault()));
        } catch (IOException exception) {
            LOGGER.error("File is not available");
        }
    }
}

