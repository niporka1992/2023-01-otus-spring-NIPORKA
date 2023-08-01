
package ru.otus.spring.controllers.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.BookRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookRestControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookRestController bookRestController;

    @Test
    @DisplayName("должен вернуть json книгу по имени")
    public void testGetBookByName() {
        // Given
        String bookName = "Book1";
        Book book = new Book("b1", "Book1",
                new Author("a1", "name1", "surname1"),
                new Genre("g1", "name1"),
                Collections.emptyList());

        when(bookRepository.findBookByName(bookName)).thenReturn(Flux.just(book));

        // When and Then
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/book/name/")
                        .queryParam("name", bookName)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .value(bookDto -> assertThat(bookDto.get(0).getName()).isEqualTo(bookName));

    }

    @Test
    @DisplayName("должен вернуть json всех книг")
    void testGetBooks() {
        // Given
        Book book1 = new Book("b1", "Book1",
                new Author("a1", "name1", "surname1"),
                new Genre("g1", "name1"),
                Collections.emptyList());
        Book book2 = new Book("b2", "Book2",
                new Author("a2", "name2", "surname2"),
                new Genre("g1", "name2"),
                Collections.emptyList());
        List<Book> books = Arrays.asList(book1, book2);
        when(bookRepository.findAll()).thenReturn(Flux.fromIterable(books));

        // When and Then
        webTestClient.get()
                .uri("/api/v1/book/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .hasSize(2)
                .contains(bookRestController.toDto(book1))
                .contains(bookRestController.toDto(book2));
    }
}


