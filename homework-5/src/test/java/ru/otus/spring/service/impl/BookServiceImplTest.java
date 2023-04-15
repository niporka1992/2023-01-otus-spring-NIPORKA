package ru.otus.spring.service.impl;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;
import ru.otus.spring.service.BookService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@DisplayName("Тесты SERVICE для работы с книгами ")
class BookServiceImplTest {
    @MockBean
    private BookDao bookDao;
    @Autowired
    private BookService bookService;
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
        given(bookDao.getCount()).willReturn(3L);
        assertEquals(3, bookService.getCount());
    }

    @Test
    @DisplayName("должен положить книгу в библиотеку")
    void save() {
        given(bookDao.insert(annaKarenina)).willReturn(annaKarenina);
        assertEquals(annaKarenina, bookService.save(annaKarenina));
    }

    @Test
    @DisplayName("должен вернуть список всех книг из библиотеки")
    void findAll() {
        var list = List.of(perfumer, harry, warAndPeace);
        given(bookDao.getAll()).willReturn(list);
        assertEquals(list, bookService.findAll());
    }

    @Test
    @DisplayName("должен достать книгу из библиотеки")
    void findById() {
        given(bookDao.getById(1)).willReturn(perfumer);
        assertEquals(perfumer, bookService.findById(1));
    }

    @Test
    @DisplayName("должен обновить книгу в библиотеке")
    void updateById() {
        given(bookDao.updateById(1, annaKarenina)).willReturn(annaKarenina);
        assertEquals(annaKarenina, bookService.updateById(1, annaKarenina));
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        given(bookDao.deleteById(1)).willReturn(1L);
        given(bookService.findById(1)).willThrow(EmptyResultDataAccessException.class);
        bookService.deleteById(1);
        assertThatThrownBy(() -> bookService.findById(1)).isInstanceOf(EmptyResultDataAccessException.class);
    }
}