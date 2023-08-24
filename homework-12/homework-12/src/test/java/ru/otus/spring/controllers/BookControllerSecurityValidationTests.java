package ru.otus.spring.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.services.AuthorService;
import ru.otus.spring.services.BookService;
import ru.otus.spring.services.CommentService;
import ru.otus.spring.services.GenreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureDataMongo
@WebMvcTest(BookController.class)
@DisplayName("Тестирование безопасности контроллеров книги")
public class BookControllerSecurityValidationTests {

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


    @DisplayName("тест аутентификации с юзером")
    @WithMockUser(username = "guest", authorities = {"ROLE_USER"})
    @Test
    public void testAuthenticatedWithUserIsOk() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("тест аутентификации без юзером")
    @Test
    public void testAuthenticatedWithoutUserIsOk() throws Exception {
        mvc.perform(get("/")).andExpect(status().is4xxClientError());
        mvc.perform(get("/edit")).andExpect(status().is4xxClientError());
    }
}
