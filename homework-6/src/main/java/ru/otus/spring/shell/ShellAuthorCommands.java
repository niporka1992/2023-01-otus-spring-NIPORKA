package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Author;
import ru.otus.spring.services.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorCommands {
    private final AuthorService authorService;

    @ShellMethod(value = "Создать автора ", key = {"add author", "aa"})
    public String addAuthor(
            @ShellOption(value = {"name"}) String name,
            @ShellOption(value = {"surname"}) String surname) {

        String author = authorService.save(name, surname);
        return "Автор сохранен - " + author;
    }

    @ShellMethod(value = "Показать всех авторов ", key = {"get all authors", "gaa"})
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }
}