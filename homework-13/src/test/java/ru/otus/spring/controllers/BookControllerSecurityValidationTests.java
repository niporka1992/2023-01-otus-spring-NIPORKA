package ru.otus.spring.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.spring.services.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @MockBean
    private UserService userService;


    @DisplayName("тест аутентификации с юзером Admin")
    @WithMockUser(username = "admin", roles = "ADMIN")
    @Test
    public void testAuthenticatedWithUserIsOk() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @DisplayName("тест аутентификации с юзером User")
    @WithMockUser(username = "guest", roles = "USER")
    @Test
    public void testAuthenticatedWithAdminIsOk() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get("/edit")).andExpect(status().is4xxClientError());
        mvc.perform(post("/edit")).andExpect(status().is4xxClientError());
        mvc.perform(post("/delete")).andExpect(status().is4xxClientError());
    }

    @DisplayName("тест аутентификации без юзером")
    @Test
    public void testAuthenticatedWithoutUserIsOk() throws Exception {
        mvc.perform(get("/**")).andExpect(status().is4xxClientError());
    }
}
