package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.entities.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
