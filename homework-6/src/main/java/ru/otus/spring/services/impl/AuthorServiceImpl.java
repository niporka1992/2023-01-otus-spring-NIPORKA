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
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public String save(String name, String surname) {
        long id = authorRepository.insertOrUpdate(new Author(name, surname)).getId();
        return "Автор c номером " + id + " " + name + " " + surname + " создан";
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return authorRepository.getById(id)
                .map(Author::toString)
                .orElse("Автора не существует.");
    }

    @Transactional(readOnly = true)
    @Override
    public List<Author> findByNameAndSurname(String name, String surname) {
        var authors = authorRepository.getByNameAndSurname(name, surname);
        return authors.isEmpty() ? Collections.emptyList() : authors;
    }

    @Transactional
    @Override
    public void updateById(long id, String name, String surname) {
        var author = new Author(id, name, surname);
        authorRepository.insertOrUpdate(author);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        authorRepository.deleteById(id);
    }
}
