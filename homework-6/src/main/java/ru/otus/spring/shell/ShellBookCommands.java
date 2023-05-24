package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.AuthorRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookCommands {
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @ShellMethod(value = "Создать книгу ", key = {"add book", "create book", "ab", "cb"})
    public String addBook(
            @ShellOption(value = {"name"}, help = "имя книги") String name,
            @ShellOption(value = {"authorId"}, help = "id автора") long authorId,
            @ShellOption(value = {"genreId"}, help = "id жанра") long genreId) {

        String book = bookService.save(name, authorId, genreId);
        return "Книга сохранена - " + book;
    }

    @ShellMethod(value = "Получить книгу по номеру", key = {"get book", "gb"})
    public String getBook(@ShellOption(value = {"id"}) long id) {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Показать все книги ", key = {"get all books", "gab"})
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @ShellMethod(value = "Удалить книгу по номеру", key = {"delete book", "db"})
    public String deleteBook(@ShellOption(value = {"id"}) long id) {
        bookService.deleteById(id);
        return "Книга с данным id удалена";
    }

    @ShellMethod(value = "Обновить информацию о книге по id", key = {"update book", "ub"})
    public String updateBook(
            @ShellOption(value = {"id"}) long id,
            @ShellOption(value = {"newName"}) String name,
            @ShellOption(value = {"authorId"}, help = "id автора") long authorId,
            @ShellOption(value = {"genreId"}, help = "id жанра") long genreId) {
        Genre genre;
        Author author;
        var authorOptional = authorRepository.getById(authorId);
        var genreOptional = genreRepository.getById(genreId);

        if (authorOptional.isPresent()) {
            author = authorOptional.get();
        } else return "Автора не существует";


        if (genreOptional.isPresent()) {
            genre = genreOptional.get();
        } else return "Жанра не существует";

        return bookService.updateById(id, new Book(name, author, genre));
    }
}