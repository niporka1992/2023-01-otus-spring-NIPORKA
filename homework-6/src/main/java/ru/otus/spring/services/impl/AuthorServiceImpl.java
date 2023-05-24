package ru.otus.spring.services.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.services.AuthorService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;

    @Transactional
    @Override
    public String save(String name, String surname) {
        long id = authorRepository.insert(new Author(name, surname)).getId();
        return id != 0 ? "Автор c номером " + id + " " + name + " " + surname + " создан" :
                "что то пошло не так.";
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
                .map(author -> "Автор  " + author)
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
    public void updateById(long id, Author author) {
        authorRepository.updateById(id, author);
        // return result ? "Автор обновился - " + author : "Ошибка";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        Optional<Author> author = authorRepository.getById(id);
        authorRepository.deleteById(id);
        if (author.isPresent()) {
            return "Автор с id " + id + " удален";
        } else {
            return "Автора с данным id не существует";
        }
    }
}
