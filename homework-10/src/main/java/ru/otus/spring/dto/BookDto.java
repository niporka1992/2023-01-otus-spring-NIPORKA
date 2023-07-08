package ru.otus.spring.dto;

import lombok.Data;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;

import java.util.List;

@Data
public final class BookDto {
    private String name;
    private Author author;
    private Genre genre;

    private List<Comment> comments;

    public BookDto(String name, Author author, Genre genre, List<Comment> comments) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.comments = comments;

    }

    public static BookDto toBookDto(Book book) {
        return new BookDto(book.getName(),
                book.getAuthor(),
                book.getGenre(),
                book.getComments());
    }
}
