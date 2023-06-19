package ru.otus.spring.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.BookRepository;
import ru.otus.spring.repositories.GenreRepository;
import ru.otus.spring.services.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;

    @Override
    public void save(String name) {
        genreRepository.save(new Genre(name));
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public String findById(String id) {
        return genreRepository.findById(id)
                .map(genre -> "Жанр - " + genre)
                .orElse("Такого жанра нет");
    }

    @Override
    public Genre findByName(String name) {
        return genreRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Жанра с таким именем нет"));
    }

    @Override
    public void updateById(String id, String name) {
        genreRepository.save(new Genre(id, name));
    }

    @Override
    public String deleteById(String id) {
        var genre = genreRepository.findById(id);
        if (genre.isPresent()) {
            List<Book> bookList = bookRepository.findBookByGenre(genre.get());
            if (bookList.size() != 0) {
                return "Жанр с id= " + id + " " + genre.get().getName() + " существует в "
                        + bookList.size() + " книгах!";
            }
        }
        genreRepository.deleteById(id);

        return "Жанр удален.";
    }
}
