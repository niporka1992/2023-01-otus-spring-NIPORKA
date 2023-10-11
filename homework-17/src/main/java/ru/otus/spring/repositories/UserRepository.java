package ru.otus.spring.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.entities.Visitor;

import java.util.Optional;

public interface UserRepository extends MongoRepository<Visitor, String> {
    Optional<Visitor> findUserByName(String name);
}
