package ru.otus.spring.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.dao.BookDao;
import ru.otus.spring.domain.Author;
import ru.otus.spring.domain.Book;
import ru.otus.spring.domain.Genre;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Тесты сервиса для работы с книгами")
class BookServiceImplTest {
    private final BookDao mockBookDao = mock(BookDao.class);
    private final BookServiceImpl bookServiceImpl = new BookServiceImpl(mockBookDao);
    ;
    private final Author joan = new Author(1, "Джоан", "Роулинг");
    private final Author patrick = new Author(3, "Патрик", "Зюскинд");
    private final Author lev = new Author(2, "Лев", "Толстой");
    private final Genre fantasy = new Genre(1, "Фэнтези");
    private final Genre novel = new Genre(2, "Роман");
    private final Book perfumer = new Book(3, "Парфюмер", patrick, novel);
    private final Book harry = new Book(1, "Гарри Поттер", joan, fantasy);
    private final Book warAndPeace = new Book(2, "Война и мир", lev, novel);
    private final Book annaKarenina = new Book("annaKarenina", new Author(3), new Genre(3));


    @Test
    @DisplayName("должен вернуть счетчик книг в библиотеке")
    void getCount() {
        when(mockBookDao.getCount()).thenReturn(3L);
        assertEquals(3, bookServiceImpl.getCount());
    }

    @Test
    @DisplayName("должен положить книгу в библиотеку")
    void save() {
        when(mockBookDao.insert(annaKarenina)).thenReturn(4L);
        assertEquals(4, bookServiceImpl.save(annaKarenina));

    }

    @Test
    @DisplayName("должен обновить книгу в библиотеке")
    void updateById() {
        var updatedBook = new Book("new", new Author(2), new Genre(2));
        bookServiceImpl.updateById(1, updatedBook);

        verify(mockBookDao, atLeastOnce()).updateById(1, updatedBook);
    }

    @Test
    @DisplayName("должен достать книгу из библиотеки")
    void findById() {
        when(mockBookDao.getById(1)).thenReturn(perfumer);
        assertEquals(perfumer, bookServiceImpl.findById(1));
    }

    @Test
    @DisplayName("должен вернуть список всех книг из библиотеки")
    void findAll() {
        var bookList = Arrays.asList(perfumer, harry, warAndPeace);

        when(mockBookDao.getAll()).thenReturn(bookList);
        assertEquals(bookList, bookServiceImpl.findAll());
    }

    @Test
    @DisplayName("должен удалить книгу из библиотеки")
    void deleteById() {
        bookServiceImpl.deleteById(1);
        verify(mockBookDao, atLeastOnce()).deleteById(1);
    }
}