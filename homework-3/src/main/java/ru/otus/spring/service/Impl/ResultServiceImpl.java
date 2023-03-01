package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PlayerDao;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.service.ReaderWriter;
import ru.otus.spring.service.ResultService;
import ru.otus.spring.service.TestingService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final PlayerDao playerDao;
    private final TestingService testingService;
    private final QuestionDao questionDao;
    private final ReaderWriter readerWriter;

    @Override
    public void showResult() {
        String result =
                playerDao.getName() +
                        testingService.getStringFromLocService("string.correct.answers") + " " +
                        testingService.getCounterOfCorrectAnswers() + " " +
                        testingService.getStringFromLocService("string.from") + " " +
                        questionDao.findAllQuestions().size() + " " +
                        testingService.getStringFromLocService("string.questions");

        readerWriter.writeToConsole(result);
    }


}

