package ru.otus.spring.dto;

import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
public record BookDto(String id,
                      @NotBlank(message = "Имя не может быть пустым") @Size(min = 3, message = "Имя слишком короткое") String name,
                      Author author, Genre genre) {
    public Book toDomainObject() {
        return new Book(this.name, this.author, this.genre);
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getGenre());
    }
}
