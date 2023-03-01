package ru.otus.spring.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.service.ApplicationRunner;
import ru.otus.spring.service.AskingService;
import ru.otus.spring.service.ResultService;
import ru.otus.spring.service.TestingService;

@Service
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final AskingService askingService;
    private final TestingService testingService;
    private final ResultService resultService;

    public void run() {
        askingService.greetPlayer();
        testingService.testing();
        resultService.showResult();
    }
}

