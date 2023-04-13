package ru.otus.spring.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.AuthorDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.service.AuthorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public long getCount() {
        return authorDao.count();
    }

    @Override
    public Author save(Author author) {
        return authorDao.insert(author);
    }

    @Override
    public List<Author> findAll() {
        return authorDao.getAll();
    }

    @Override
    public Author findById(long id) {
        return authorDao.getById(id);
    }

    @Override
    public Author findByNameAndSurname(String name, String surname) {
        return authorDao.getByNameAndSurname(name, surname);
    }

    @Override
    public Author updateById(long id, String name, String surname) {
        return authorDao.updateById(id, name, surname);
    }

    @Override
    public long deleteById(long id) {
        return authorDao.deleteById(id);
    }
}
