package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.dao.PlayerDao;
import ru.otus.spring.service.QuestionService;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final QuestionService questionService;
    private final PlayerDao playerDao;

    @ShellMethod("Greeting")
    public void login() {
        questionService.askName();
    }

    @ShellMethod("Run testing")
    @ShellMethodAvailability(value = "isStartTestingAvailable")
    public void run() {
        questionService.testing();
        questionService.showResult();
    }

    private Availability isStartTestingAvailable() {
        System.out.println(playerDao.getName());
        return (playerDao.getName() == null) ? Availability.unavailable("You need to login before testing.") : Availability.available();
    }
}
