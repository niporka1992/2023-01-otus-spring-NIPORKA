package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public String save(String name, String surname) {
        long id = authorRepository.insert(new Author(name, surname)).getId();
        return id != 0 ? "Автор c номером " + id + " " + name + " " + surname + " создан" :
                "что то пошло не так.";
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAll() {
        return authorRepository.getAll().stream().map(
                Author::toString).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        if (authorRepository.getById(id).isPresent()) {
            var author = authorRepository.getById(id).get();
            return "Автор  " + author;
        } else return "Автора не существует.";
    }

    @Transactional(readOnly = true)
    @Override
    public String findByNameAndSurname(String name, String surname) {
        var authors = authorRepository.getByNameAndSurname(name, surname).stream().map(
                x -> "Автор  " + x.toString()).toList();
        return authors.isEmpty() ? "Автора(ов) не существует." : authors.toString();
    }

    @Transactional
    @Override
    public String updateById(long id, String name, String surname) {
        Author author = new Author(id, name, surname);
        boolean result = authorRepository.updateById(id, name, surname);
        return result ? "Автор обновился - " + author : "Ошибка";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        boolean result = authorRepository.deleteById(id);
        return result ? "Автор с id " + id + " удален" : "Автора с данным id не существует";
    }
}
