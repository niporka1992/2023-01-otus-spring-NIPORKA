package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.entities.Author;
import ru.otus.spring.entities.Book;
import ru.otus.spring.entities.Genre;

public interface BookRepository extends MongoRepository<Book, String> {
    Long countBookByAuthor(Author author);

    Long countBookByGenre(Genre genre);

}
