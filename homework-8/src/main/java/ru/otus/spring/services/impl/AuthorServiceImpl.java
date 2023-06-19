package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.services.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public void save(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }


    @Override
    public String findById(String id) {
        return authorRepository.findById(id)
                .map(Author::toString)
                .orElse("Автора не существует.");
    }


    @Override
    public Optional<Author> findByNameAndSurname(String name, String surname) {
        return authorRepository.findByNameAndSurname(name, surname);
    }


    @Override
    public void updateById(String id, String name, String surname) {
        var author = new Author(id, name, surname);
        authorRepository.save(author);
    }


    @Override
    public String deleteById(String id) {
        var author = authorRepository.findById(id);
        if (author.isPresent()) {
            List<Book> bookList = bookRepository.findBookByAuthor(author.get());
            if (bookList.size() != 0) {
                return "Автор с id= " + id + " " + author.get().getName() + author.get().getSurname() + " существует в "
                        + bookList.size() + " книгах!";
            }
        }
        authorRepository.deleteById(id);
        return "Автор удален.";
    }
}
