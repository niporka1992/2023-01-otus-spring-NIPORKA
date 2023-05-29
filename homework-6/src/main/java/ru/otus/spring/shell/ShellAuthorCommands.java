package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Author;
import ru.otus.spring.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

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
    public String getAllAuthors() {
        List<Author> all = authorService.findAll();
        return getAuthorsAsString(all);
    }

    @ShellMethod(value = "Обновить автора по id", key = {"update author by id", "ua"})
    public void updateAuthorById(
            @ShellOption(value = {"id"}) long id,
            @ShellOption(value = {"name"}) String name,
            @ShellOption(value = {"surname"}) String surname) {
        authorService.updateById(id, name, surname);
    }

    private String getAuthorsAsString(List<Author> authors) {
        return format("Список авторов:\n\t%s", authors.stream()
                .map(Author::toString)
                .collect(Collectors.joining("\n\t"))
        );
    }
}