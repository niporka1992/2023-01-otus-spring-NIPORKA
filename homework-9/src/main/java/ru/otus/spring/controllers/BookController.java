package ru.otus.spring.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.dto.BookDto;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.services.AuthorService;
import ru.otus.spring.services.BookService;
import ru.otus.spring.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;


    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("books", bookList);
        return "list";
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") String id, Model model) {

        Book book = bookService.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Книги с таким ID нет"));
        addAttributes(model, book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto bookDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addAttributes(model);
            return "edit";
        }


        if (bookDto.id().equals(" ")) {
            bookService.save(bookDto.toDomainObject());
        } else {
            bookService.save(new Book(bookDto.id(),
                    bookDto.name(),
                    bookDto.author(),
                    bookDto.genre()));
        }
        return "redirect:/";

    }

    @GetMapping("/create")
    public String createBook(Model model) {
        Book book = new Book(" ");
        addAttributes(model, book);
        return "edit";
    }


    @PostMapping("/delete")
    public String deleteBook(@RequestParam("id") String id) {
        bookService.deleteById(id);
        return "redirect:/";
    }

    private void addAttributes(Model model, Book book) {
        model.addAttribute("book", BookDto.toDto(book));

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

    }

    private void addAttributes(Model model) {

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

    }
}
