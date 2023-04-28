package ru.otus.spring.repositories.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@DisplayName("Тесты JPA для работы с книгами")
@Import(BookRepositoryJpa.class)
@DataJpaTest
class BookRepositoryJpaTest {
    @Autowired
    private BookRepository bookRepository;
    private final Comment comment3 = new Comment(3, "text3", 2);
    private final Author patrick = new Author(3, "Патрик", "Зюскинд");
    private final Genre fantasy = new Genre(1, "Фэнтези");


    @Test
    @DisplayName("должен положить книгу в библиотеку")
    void insert() {
        Book expected = new Book(
                4,
                "name",
                new Author(4, "author", "author"),
                new Genre(3, "genre"));
        bookRepository.insert(expected);
        var actual = bookRepository.getById(4).get();
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("должен обновить книгу в библиотеке")
    void updateById() {
        Book expected = new Book(2, "Парфюмер", patrick, fantasy, List.of(comment3));
        bookRepository.updateById(2, expected);
        var actual = bookRepository.getById(2).get();
        assertThat(
                String.valueOf(actual.getId()) +
                        actual.getGenre() +
                        actual.getAuthor() +
                        actual.getName() +
                        actual.getComments()).isEqualTo(

                String.valueOf(expected.getId()) +
                        expected.getGenre() +
                        expected.getAuthor() +
                        expected.getName() +
                        expected.getComments());
    }

    @Test
    @DisplayName("должен достать книгу из библиотеки")
    void getById() {
        Book expected = bookRepository.getById(1).get();
        assertThat(1).isEqualTo(expected.getId());
    }


    @Test
    @DisplayName("должен вернуть список всех книг из библиотеки")
    void getAll() {
        var bookList = bookRepository.getAll();
        Assertions.assertEquals(3, bookList.size());
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        bookRepository.deleteById(1);
        assertThatThrownBy(() -> bookRepository.getById(1).get()).isInstanceOf(NoSuchElementException.class);
    }
}