package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public void save(String name) {
        genreRepository.save(new Genre(name));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return genreRepository.findById(id)
                .map(genre -> "Жанр - " + genre)
                .orElse("Такого жанра нет");
    }

    @Transactional(readOnly = true)
    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Жанра с таким именем нет"));
    }

    @Transactional
    @Override
    public void updateById(long id, String name) {
        genreRepository.save(new Genre(id, name));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        genreRepository.deleteById(id);
    }
}
