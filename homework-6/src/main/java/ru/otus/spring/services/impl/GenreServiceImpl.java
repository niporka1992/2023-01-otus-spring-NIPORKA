package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.GenreService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor

public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public String save(String name) {
        Genre genre = new Genre(name);
        if (genreRepository.getByName(name).isEmpty()) {
            genreRepository.insert(genre);
            return "Жанр " + genre.getName() + " добавлен";
        } else {
            return "Такой жанр имеется.";
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepository.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return genreRepository.getById(id)
                .map(genre -> "Жанр - " + genre)
                .orElse("Такого жанра нет");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findByName(String name) {
        if (genreRepository.getByName(name).isEmpty()) {
            return Collections.emptyList();
        }
        return genreRepository.getByName(name);
    }

    @Transactional
    @Override
    public void updateById(long id, String name) {
        genreRepository.updateById(id, name);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
