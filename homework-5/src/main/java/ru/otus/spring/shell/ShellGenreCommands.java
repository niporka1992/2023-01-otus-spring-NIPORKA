package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Создать жанр ", key = {"add genre", "ag"})
    public String addAuthor(@ShellOption(value = {"name"}) String name) {

        long id = genreService.save(new Genre(name));
        return "Жанр сохранен с id - " + id;
    }
}
