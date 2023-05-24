package ru.otus.spring.repositories.impl;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Comment;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.BookRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Тесты JPA для работы с книгами")
@Import(BookRepositoryJpa.class)
@DataJpaTest
class BookRepositoryJpaTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager em;
    private final Comment comment3 = new Comment(3, "text3", new Book(2));
    private final Author patrick = new Author(3, "Патрик", "Зюскинд");
    private final Genre fantasy = new Genre(1, "Фэнтези");

    @Test
    @DisplayName("должен положить книгу в библиотеку")
    void insert() {
        Book expected = new Book(4, "ff", patrick, fantasy);
        bookRepository.insert(expected);
        val actual = em.find(Book.class, 4);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("должен обновить книгу в библиотеке")
    void updateById() {
        Book expected = new Book(2, "Парфюмер", patrick, fantasy);
        bookRepository.updateById(2, expected);
        var actual = em.find(Book.class, 2);
        assertThat(
                String.valueOf(actual.getId()) +
                        actual.getGenre() +
                        actual.getAuthor() +
                        actual.getName()).isEqualTo(

                String.valueOf(expected.getId()) +
                        expected.getGenre() +
                        expected.getAuthor() +
                        expected.getName());
    }

    @Test
    @DisplayName("должен достать книгу из библиотеки")
    void getById() {
        val optionalActualBook = bookRepository.getById(1);
        val expectedBook = em.find(Book.class, 1);
        assertThat(optionalActualBook).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("должен вернуть список всех книг c авторами и жанрами из библиотеки")
    void getAll() {
        val bookList = bookRepository.getAllWithAuthorAndGenre();
        assertThat(bookList).isNotNull().hasSize(3)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getComments() != null)
                .allMatch(s -> s.getGenre() != null);
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        bookRepository.deleteById(1);
        val book = em.find(Book.class, 1);
        assertNull(book);
    }
}