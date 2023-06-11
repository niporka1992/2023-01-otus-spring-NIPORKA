package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.services.GenreService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ShellComponent
@RequiredArgsConstructor
public class ShellGenreCommands {
    private final GenreService genreService;

    @ShellMethod(value = "Создать жанр ", key = {"add genre", "ag"})
    public void addGenre(@ShellOption(value = {"name"}) String name) {
         genreService.save(name);
    }

    @ShellMethod(value = "Показать все жанры ", key = {"get all genres", "gag"})
    public String getAllGenres() {
        List<Genre> all = genreService.findAll();
        return getGenresAsString(all);
    }

    private String getGenresAsString(List<Genre> genres) {
        return format("Список жанров:\n\t%s", genres.stream()
                .map(Genre::toString)
                .collect(Collectors.joining("\n\t"))
        );
    }
}