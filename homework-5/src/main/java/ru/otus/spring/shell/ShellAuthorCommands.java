package ru.otus.spring.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellAuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(value = "Создать автора ", key = {"add author", "aa"})
    public String addAuthor(
            @ShellOption(value = {"name"}) String name,
            @ShellOption(value = {"surname"}) String surname) {

        Author author = authorService.save(new Author(name, surname));
        return "Автор сохранен - " + author.id();
    }

    @ShellMethod(value = "Показать всех авторов ", key = {"get all authors", "gaa"})
    public String getAllAuthors() {
        List<Author> authorList = authorService.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        authorList.forEach(x -> stringBuilder.append(x).append("\n"));

        return stringBuilder.toString();
    }
}
