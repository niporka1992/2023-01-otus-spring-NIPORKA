package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookCommands {

    private final BookService bookService;


    @ShellMethod(value = "Счетчик книг", key = {"books count", "bc"})
    public String booksCount() {
        var count = bookService.getCount();
        return "Кол-во книг в библиотеке - " + count + " шт.";
    }

    @ShellMethod(value = "Создать книгу ", key = {"add book", "create book", "ab", "cb"})
    public String addBook(
            @ShellOption(value = {"name"}, help = "имя книги") String name,
            @ShellOption(value = {"authorId"}, help = "id автора") long authorId,
            @ShellOption(value = {"genreId"}, help = "id жанра") long genreId) {

        long id = bookService.save(new Book(name, new Author(authorId), new Genre(genreId)));
        return "Книга сохранена с id - " + id;
    }

    @ShellMethod(value = "Получить книгу по номеру", key = {"get book", "gb"})
    public String getBook(@ShellOption(value = {"id"}) long id) {
        Book book = bookService.findById(id);
        return "Название книги - " + book.name();
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
        bookService.updateById(id, new Book(name, new Author(authorId), new Genre(genreId)));
        return "Книга с данным id обновлена";
    }


}

