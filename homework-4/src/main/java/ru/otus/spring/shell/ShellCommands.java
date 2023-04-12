package ru.otus.spring.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.service.TestingService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final TestingService testingService;

    @ShellMethod(value = "Testing", key = {"r", "run", "t", "test"})
    public void testing() {
        testingService.executeExam();
    }
}
