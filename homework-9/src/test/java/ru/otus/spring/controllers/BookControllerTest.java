package ru.otus.spring.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.services.AuthorService;
import ru.otus.spring.services.BookService;
import ru.otus.spring.services.CommentService;
import ru.otus.spring.services.GenreService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureDataMongo
@WebMvcTest(BookController.class)
@DisplayName("Тестирование контроллеров киниги")
class BookControllerTest {

    private final List<Author> allAuthors = List.of(new Author("1L", "Author1"),
            new Author("2L", "Author2"));
    private final List<Genre> allGenres = List.of(new Genre("1L", "Genre1"),
            new Genre("2L", "Genre2"));
    private final Book book = new Book("1L", "The Book", allAuthors.get(0), allGenres.get(0));

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentService commentService;


    @DisplayName("контроллер должен отобразить информацию всех книг")
    @Test
    void listPage() throws Exception {
        List<Book> bookList = List.of(book);
        given(bookService.findAll()).willReturn(bookList);
        mvc.perform(get("/"))
                .andExpectAll(
                        view().name("list"),
                        model().attribute("books", bookList),
                        status().isOk(),
                        content().string(stringContainsInOrder(
                                book.getId(),
                                book.getName(),
                                book.getAuthor().getName(),
                                book.getGenre().getName()))
                );
    }

    @DisplayName("должен отобразить страницу редактирования книги")
    @Test
    void editBook() throws Exception {
        Optional<Book> book1 = Optional.of(new Book("book", new Author("a", "s"), new Genre("g")));
        given(bookService.findById("b1")).willReturn(book1);
        this.mvc.perform(get("/edit?id=b1")).andExpectAll(
                view().name("edit"),
                status().isOk());
    }

    @DisplayName("должен создать книгу")
    @Test
    void testSaveBook() throws Exception {
        Optional<Book> book1 = Optional.of(new Book(" ", new Author("a", "s"), new Genre("g")));
        given(bookService.findById("b1")).willReturn(book1);
        this.mvc.perform(post("/edit"))
                .andExpect(status().isOk());
    }

    @DisplayName("должен отобразить страницу создания книги")
    @Test
    void testCreateBook() throws Exception {
        mvc.perform(get("/create")).andExpectAll(
                view().name("edit"),
                status().isOk());
    }


    @DisplayName("должен удалить книгу и вернуть на гл страницу")
    @Test
    void testDeleteBook() throws Exception {
        mvc.perform(post("/delete?id=1"))
                .andExpect(status().is3xxRedirection());
    }
}