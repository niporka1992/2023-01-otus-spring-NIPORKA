package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

/*    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public void save(String name) {
        genreRepository.save(new Genre(name));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(String id) {
        return genreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Жанра с таким ID нет"));
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Жанра с таким именем нет"));
    }

    @Override
    public void updateById(String id, String name) {
        genreRepository.save(new Genre(id, name));
    }

    @Override
    public String deleteById(String id) {
        var genre = genreRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Жанра не существует."));
        var count = bookRepository.countBookByGenre(genre);
        if (count > 0) {
            return "Жанр с id= " + id + " " + genre.getName() + " существует в "
                    + count + " книге(ах)!";
        } else {
            genreRepository.deleteById(id);
            return "Жанр удален.";
        }
    }*/
}
