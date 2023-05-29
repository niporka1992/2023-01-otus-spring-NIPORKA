package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Author;
import ru.otus.spring.exceptions.EntityNotFoundException;
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
    public void update(Author author) throws EntityNotFoundException {
        long id = author.getId();
        Author currentAuthor = getById(id).orElseThrow(() -> new EntityNotFoundException("Автора не существует"));
        currentAuthor.setName(author.getName());
        currentAuthor.setSurname(author.getSurname());
        em.merge(currentAuthor);
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
    public void deleteById(long id) throws EntityNotFoundException {
        Author author = getById(id).orElseThrow(() -> new EntityNotFoundException("Автора не существует"));
        em.remove(author);
    }
}
