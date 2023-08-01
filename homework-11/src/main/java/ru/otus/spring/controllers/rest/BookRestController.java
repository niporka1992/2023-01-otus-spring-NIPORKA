package ru.otus.spring.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.BookRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class BookRestController {


    private final BookRepository bookRepository;

    @GetMapping(path = "/v1/book/name/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BookDto> getBooksByName(@RequestParam("name") String name) {
        return bookRepository.findBookByName(name).map(this::toDto);
    }

    @GetMapping(path = "/v1/book/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BookDto> getBooks() {
        Flux<Book> all = bookRepository.findAll();
        return all.map(this::toDto);
    }

    public final BookDto toDto(Book book) {
        return new BookDto(book.getName(), book.getAuthor(), book.getGenre(), book.getComments());
    }
}