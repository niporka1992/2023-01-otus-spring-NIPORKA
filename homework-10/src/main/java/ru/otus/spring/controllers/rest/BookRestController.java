package ru.otus.spring.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Book;
import ru.otus.spring.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookRestController {
    private final BookService bookService;


    @GetMapping("/v1/book/name/")
    public List<BookDto> getBooksByName(@RequestParam("name") String name) {
        return bookService.findByName(name);
    }

    @GetMapping("/v1/book/")
    public List<BookDto> getBooks() {
        List<Book> all = bookService.findAll();
        return all.stream().map(book -> new BookDto(
                book.getName(), book.getAuthor(), book.getGenre(), book.getComments())).collect(Collectors.toList());
    }
}