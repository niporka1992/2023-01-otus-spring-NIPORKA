package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AnswerDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Answer;
import ru.otus.spring.service.ReaderWriter;
import ru.otus.spring.service.TestingService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestingServiceImpl implements TestingService {

    private final AnswerDao answerDao;
    private final QuestionDao questionDao;
    private final ReaderWriter readerWriter;
    private int countRightAnswer = 0;

    @Override
    public void testing() {

        List<Answer> answerList = answerDao.findAllAnswers();
        questionDao.findAllQuestions().forEach(question -> {
                    readerWriter.writeToConsole(question.getName());
                    Answer answer = new Answer(readerWriter.read());
                    if (!answerList.contains(answer)) return;
                    answerList.remove(answer);
                    countRightAnswer++;
                }
        );
    }

    @Override
    public int getCounterOfCorrectAnswers() {
        return countRightAnswer;
    }

    public String getStringFromLocService(String messageProperty) {
        return readerWriter.getString(messageProperty);
    }
}
