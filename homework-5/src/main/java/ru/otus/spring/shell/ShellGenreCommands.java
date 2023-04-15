package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreCommands {

    private final GenreService genreService;

    @ShellMethod(value = "Создать жанр ", key = {"add genre", "ag"})
    public String addAuthor(@ShellOption(value = {"name"}) String name) {

        Genre genre = genreService.save(new Genre(name));
        return "Жанр сохранен - " + genre;
    }

    @ShellMethod(value = "Показать все жанры ", key = {"get all genres", "gag"})
    public String getAllAuthors() {
        List<Genre> genreList = genreService.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        genreList.forEach(x -> stringBuilder.append(x).append("\n"));

        return stringBuilder.toString();
    }
}
