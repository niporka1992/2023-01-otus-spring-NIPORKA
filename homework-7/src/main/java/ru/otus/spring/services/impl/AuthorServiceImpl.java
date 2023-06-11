package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.services.AuthorService;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public void save(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return authorRepository.findById(id)
                .map(Author::toString)
                .orElse("Автора не существует.");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findByNameAndSurname(String name, String surname) {
        List<Author> authors = authorRepository.findByNameAndSurname(name, surname);
        return authors.isEmpty() ? Collections.emptyList() : authors;
    }

    @Transactional
    @Override
    public void updateById(long id, String name, String surname) {
        var author = new Author(id, name, surname);
        authorRepository.save(author);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }
}
