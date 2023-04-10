package ru.otus.spring.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Создать автора ", key = {"add author", "aa"})
    public String addAuthor(
            @ShellOption(value = {"name"}) String name,
            @ShellOption(value = {"surname"}) String surname) {

        long id = authorService.save(new Author(name, surname));
        return "Автор сохранен с id - " + id;
    }

}
