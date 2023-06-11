package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public String save(String name, long authorId, long genreId) {
        Author author = authorRepository.getById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Автора с таким ID не существует."));
        Genre genre = genreRepository.getById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Жанра с таким ID не существует."));
        Book book = bookRepository.insert(new Book(name, author, genre));
        return "Книга c номером " + book.getId() + " " + name + " " + " создана";
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.getAllWithAuthorAndGenre();
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        return bookRepository.getById(id)
                .map(Book::toString)
                .orElse("Такой книги нет");
    }


    @Transactional
    @Override
    public void updateById(long id, String name, long authorId, long genreId) throws EntityNotFoundException {
        var author = authorRepository.getById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Такого автора не существует"));
        var genre = genreRepository.getById(genreId)
                .orElseThrow(() -> new EntityNotFoundException("Такого жанра не существует"));
        var book = new Book(id, name, author, genre);
        bookRepository.update(book);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}
