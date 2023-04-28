package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public String save(String name) {
        if (genreRepository.getByName(name).isEmpty()) {
            return genreRepository.insert(new Genre(name)).get().toString();
        } else return "Такой жанр имеется.";
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAll() {
        return genreRepository.getAll().stream().map(Genre::toString).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        if (genreRepository.getById(id).isPresent()) {
            return "Жанр найден.";
        }
        return "Жанра не существует";
    }

    @Transactional(readOnly = true)
    @Override
    public String findByName(String name) {
        if (genreRepository.getByName(name).isEmpty()) {
            return "Жанра не существует";
        }
        return genreRepository.getByName(name).toString();
    }

    @Transactional
    @Override
    public String updateById(long id, String name) {
        if (genreRepository.getById(id).isPresent()) {
            boolean result = genreRepository.updateById(id, name);
            return result ? "Жанр обновлен." : "Ошибка";
        }
        return "Такого жанра нет";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        if (genreRepository.getById(id).isPresent()) {
            boolean result = genreRepository.deleteById(id);
            return result ? "Жанр удален." : "Ошибка";
        }
        return "Такого жанра нет";
    }
}
