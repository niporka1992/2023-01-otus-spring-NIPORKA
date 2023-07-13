package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Author;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.services.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
 /*   private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;*/

/*
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    @Override
    public Optional<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

*/


/*    @Override
    public void updateById(String id, String name, String surname) {
        var author = new Author(id, name, surname);
        authorRepository.save(author);
    }


    @Override
    public String deleteById(String id) {
        var author = authorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Автора не существует."));
        var count = bookRepository.countBookByAuthor(author);
        if (count > 0) {
            return "Автор с id= " + id + " " + author.getName() + " " + author.getSurname() + " существует в "
                    + count + " книге(ах)!";
        } else {
            authorRepository.deleteById(id);
            return "Автор удален.";
        }
    }*/
}

