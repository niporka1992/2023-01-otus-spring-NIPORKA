package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public String save(String name, String authorId, String genreId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Автора с таким ID не существует."));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Жанра с таким ID не существует."));
        Book book = bookRepository.save(new Book(name, author, genre));
        return "Книга c номером " + book.getId() + " " + name + " " + " создана";
    }


    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public String findById(String id) {
        return bookRepository.findById(id)
                .map(Book::toString)
                .orElse("Такой книги нет");
    }


    @Override
    public void updateById(String id, String name, String authorId, String genreId) throws EntityNotFoundException {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Такого автора не существует"));
        var genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Такого жанра не существует"));
        var book = new Book(id, name, author, genre);
        bookRepository.save(book);
    }

    @Override
    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }
}
