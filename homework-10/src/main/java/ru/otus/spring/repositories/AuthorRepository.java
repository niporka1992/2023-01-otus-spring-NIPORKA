package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.entities.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
 //   Optional<Author> findByName(String name);
}





