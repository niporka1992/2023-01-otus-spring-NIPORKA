package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.entities.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
/*    Optional<Genre> findByName(String name);*/
}
