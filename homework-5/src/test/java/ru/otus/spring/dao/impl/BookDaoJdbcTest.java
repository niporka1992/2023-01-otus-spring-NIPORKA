package ru.otus.spring.dao.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


@DisplayName("Тесты DAO для работы с книгами")
@Import(BookDaoJdbc.class)
@JdbcTest
class BookDaoJdbcTest {

    @Autowired
    private BookDao bookDao;
    private final Author joan = new Author(1, "Джоан", "Роулинг");
    private final Author patrick = new Author(3, "Патрик", "Зюскинд");
    private final Author lev = new Author(2, "Лев", "Толстой");
    private final Genre fantasy = new Genre(1, "Фэнтези");
    private final Genre novel = new Genre(2, "Роман");
    private final Book perfumer = new Book(3, "Парфюмер", patrick, novel);
    private final Book harry = new Book(1, "Гарри Поттер", joan, fantasy);
    private final Book warAndPeace = new Book(2, "Война и мир", lev, novel);
    private final Book annaKarenina = new Book("annaKarenina", new Author(2), new Genre(2));


    @Test
    @DisplayName("должен вернуть счетчик книг в библиотеке")
    void getCount() {
        Assertions.assertEquals(3, bookDao.getCount());
    }

    @Test
    @DisplayName("должен положить книгу в библиотеку")
    void insert() {

        bookDao.insert(annaKarenina);
        Book actualBook = bookDao.getById(4);

        assertThat(annaKarenina).usingRecursiveComparison().isEqualTo(annaKarenina);
    }

    @Test
    @DisplayName("должен обновить книгу в библиотеке")
    void updateById() {
        Book expectedBook = new Book(1, "Парфюмер", lev, novel);
        bookDao.updateById(1, expectedBook);

        assertThat(bookDao.getById(1)).isEqualTo(expectedBook);
    }

    @Test
    @DisplayName("должен достать книгу из библиотеки")
    void getById() {
        assertThat(bookDao.getById(3)).isEqualTo(perfumer);
    }

    @Test
    @DisplayName("должен вернуть список всех книг из библиотеки")
    void getAll() {
        var bookList = bookDao.getAll();
        assertThat(bookList, containsInAnyOrder(
                harry,
                perfumer,
                warAndPeace
        ));
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        bookDao.deleteById(1);
        assertThatThrownBy(() -> bookDao.getById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}