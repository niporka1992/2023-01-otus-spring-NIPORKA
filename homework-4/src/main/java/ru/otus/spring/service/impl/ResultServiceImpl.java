package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.TestingResult;
import ru.otus.spring.service.ResultService;
import ru.otus.spring.service.l10n.LocalizationIOService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final LocalizationIOService localizationIOService;
    private final static String SEPARATOR = " ";

    @Override
    public void show(TestingResult result) {
        String name = result.student().name();
        String answers = localizationIOService.getMessage("string.correct.answers") + SEPARATOR;
        String from = localizationIOService.getMessage(("string.from")) + SEPARATOR;
        String questions = localizationIOService.getMessage("string.questions") + SEPARATOR;
        String totalQuestions = result.totalQuestions() + SEPARATOR;
        String score = result.score() + SEPARATOR;

        localizationIOService.outputMessage(name + answers + score + from + totalQuestions + questions);
    }
}

