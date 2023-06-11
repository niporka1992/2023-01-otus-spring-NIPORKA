package ru.otus.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.entities.Book;
import ru.otus.spring.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ShellComponent
@RequiredArgsConstructor
public class ShellBookCommands {
    private final BookService bookService;

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
    public String getAllBooks() {
        List<Book> all = bookService.findAll();
        return getBooksAsString(all);
    }

    @ShellMethod(value = "Удалить книгу по номеру", key = {"delete book", "db"})
    public void deleteBook(@ShellOption(value = {"id"}) long id) {
        bookService.deleteById(id);
    }

    @ShellMethod(value = "Обновить информацию о книге по id", key = {"update book", "ub"})
    public void updateBookById(
            @ShellOption(value = {"id"}) long id,
            @ShellOption(value = {"newName"}) String name,
            @ShellOption(value = {"authorId"}, help = "id автора") long authorId,
            @ShellOption(value = {"genreId"}, help = "id жанра") long genreId) {

        bookService.updateById(id, name, authorId, genreId);
    }

    private String getBooksAsString(List<Book> books) {
        return format("Список книг:\n\t%s", books.stream()
                .map(this::converter)
                .collect(Collectors.joining("\n\t"))
        );
    }

    private String converter(Book book) {
        return "Id - " + book.getId() +
                " Имя книги - " + book.getName() +
                " Id автора - " + book.getAuthor().getId() +
                " Id жанра - " + book.getGenre().getId();
    }
}