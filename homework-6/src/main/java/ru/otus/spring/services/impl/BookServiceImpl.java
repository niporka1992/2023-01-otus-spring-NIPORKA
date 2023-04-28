package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Transactional
    @Override
    public String save(String name, long authorId, long genreId) {
        Author author = authorRepository.getById(authorId).orElse(null);
        Genre genre = genreRepository.getById(genreId).orElse(null);
        if (author == null || genre == null) {
            return "Таких ID не существует";
        }
        Book book = bookRepository.insert(new Book(name, author, genre));
        return "Книга c номером " + book.getId() + " " + name + " " + " создана";
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findAll() {
        return bookRepository.getAll().stream().map(Book::toString).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public String findById(long id) {
        Optional<Book> book = bookRepository.getById(id);
        if (book.isEmpty()) {
            return "Такой книги нет";
        }
        return book.get().toString();
    }

    @Transactional
    @Override
    public String updateById(long id, Book book) {
        if (bookRepository.getById(id).isPresent()) {
            boolean result = bookRepository.updateById(id, book);
            return result ? "Книга обновлена." : "Ошибка";
        }
        return "Такой книги нет";
    }

    @Transactional
    @Override
    public String deleteById(long id) {
        if (bookRepository.getById(id).isPresent()) {
            boolean result = bookRepository.deleteById(id);
            return result ? "Книга удалена" : "Ошибка";
        } else return "Такой книги нет";
    }
}
