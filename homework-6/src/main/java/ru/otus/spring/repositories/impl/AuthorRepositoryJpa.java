package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Author;
import ru.otus.spring.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author insert(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            return author;
        }
        return em.merge(author);
    }

    @Override
    public boolean updateById(long id, String name, String surname) {
        var query = em.createQuery(
                "update Author a set a.name = :name, a.surname = :surname where a.id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getByNameAndSurname(String name, String surname) {
        var query = em.createQuery(
                "select a from Author a where a.name = :name and a.surname = :surname", Author.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);
        return query.getResultList();
    }

    @Override
    public List<Author> getAll() {
        var query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        var query = em.createQuery("delete from Author a where a.id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }
}
