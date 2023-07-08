package ru.otus.spring.controllers.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.services.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureDataMongo
@DisplayName("Тестирование контроллеров книги")
@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("должен вернуть json книгу по имени")
    public void testGetBookByName() throws Exception {

        String bookName = "Book";
        Book book = new Book("b3", "Book",
                new Author("a2", "pa", "ga"),
                new Genre("g1", "r"),
                Collections.emptyList());
        List<BookDto> bookList = new ArrayList<>();
        bookList.add(BookDto.toBookDto(book));

        when(bookService.findByName(bookName)).thenReturn(bookList);

        ResultActions resultActions = mockMvc.perform(get("/api/v1/book/{name}", bookName)
                .accept(MediaType.APPLICATION_JSON));

        String expectedJsonArray = "[{\"name\":\"Book\",\"author\":{\"id\":\"a2\",\"name\":\"pa\",\"surname\":\"ga\"}," +
                "\"genre\":{\"id\":\"g1\",\"name\":\"r\"},\"comments\":[]}]";

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJsonArray));

    }

    @Test
    @DisplayName("должен вернуть json всех книг")
    void getBooks() throws Exception {

        Book book1 = (new Book("b3", "Book1",
                new Author("a2", "name1", "surname1"),
                new Genre("g1", "name1"),
                Collections.emptyList()));
        Book book2 = (new Book("b3", "Book2",
                new Author("a2", "name2", "surname2"),
                new Genre("g1", "name2"),
                Collections.emptyList()));
        List<Book> books = Arrays.asList(book1, book2);

        when(bookService.findAll()).thenReturn(books);

        ResultActions resultActions = mockMvc.perform(get("http://localhost:8080/api/v1/book/")
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Book1"))
                .andExpect(jsonPath("$[0].author.name").value("name1"))
                .andExpect(jsonPath("$[0].author.surname").value("surname1"))
                .andExpect(jsonPath("$[0].genre.name").value("name1"))

                .andExpect(jsonPath("$[1].name").value("Book2"))
                .andExpect(jsonPath("$[1].author.name").value("name2"))
                .andExpect(jsonPath("$[1].author.surname").value("surname2"))
                .andExpect(jsonPath("$[1].genre.name").value("name2"));

    }
}