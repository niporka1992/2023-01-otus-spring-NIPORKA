package ru.otus.spring.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureDataMongo
@WebMvcTest(BookController.class)
@DisplayName("Тестирование контроллеров киниги")
class BookControllerTest {

    @Autowired
    private MockMvc mvc;


    @DisplayName("контроллер должен вернуть вью list")
    @Test
    void listPage() throws Exception {
        mvc.perform(get("/"))
                .andExpectAll(
                        view().name("list"),
                        status().isOk());
    }
}