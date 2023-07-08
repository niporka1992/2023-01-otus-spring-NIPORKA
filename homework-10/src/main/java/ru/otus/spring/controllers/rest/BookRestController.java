package ru.otus.spring.controllers.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Book;
import ru.otus.spring.services.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;


    @GetMapping("/api/v1/book/{name}")
    public List<BookDto> getBooksByName(@PathVariable("name") String name) {

        return bookService.findByName(name);
    }

    @GetMapping("/api/v1/book/")
    public List<BookDto> getBooks() {
        List<Book> all = bookService.findAll();
        return all.stream().map(book -> new BookDto(
                book.getName(), book.getAuthor(), book.getGenre(), book.getComments())).collect(Collectors.toList());
    }
}