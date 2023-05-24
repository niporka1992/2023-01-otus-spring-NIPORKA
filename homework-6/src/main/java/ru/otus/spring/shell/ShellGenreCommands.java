package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.services.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreCommands {
    private final GenreService genreService;

    @ShellMethod(value = "Создать жанр ", key = {"add genre", "ag"})
    public String addGenre(@ShellOption(value = {"name"}) String name) {

        return "Жанр создан - " + genreService.save(name);
    }

    @ShellMethod(value = "Показать все жанры ", key = {"get all genres", "gag"})
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }
}