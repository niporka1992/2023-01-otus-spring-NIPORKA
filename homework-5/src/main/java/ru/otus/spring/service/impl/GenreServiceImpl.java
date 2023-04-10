package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GenreDao;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public long getCount() {
        return genreDao.count();
    }

    @Override
    public long save(Genre genre) {
        return genreDao.insert(genre);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre findById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre findByName(String name) {
        return genreDao.getByName(name);
    }

    @Override
    public long updateById(long id, Genre genre) {
        return genreDao.updateById(id, new Genre(genre.name()));
    }

    @Override
    public long deleteById(long id) {
        return genreDao.deleteById(id);
    }
}
