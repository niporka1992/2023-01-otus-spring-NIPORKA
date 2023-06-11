package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.exceptions.EntityNotFoundException;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return genre;
        }
        return em.merge(genre);
    }

    @Override
    public void updateById(long id, String name) throws EntityNotFoundException {
        Genre genre = getById(id).orElseThrow(() -> new EntityNotFoundException("Такого жанра нет"));
        genre.setName(name);
        em.merge(genre);
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public List<Genre> getByName(String name) {
        var query = em.createQuery(
                "select g from Genre g where g.name= :name", Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) throws EntityNotFoundException {
        Genre genre = getById(id).orElseThrow(() -> new EntityNotFoundException("Такого жанра нет"));
        em.remove(genre);
    }
}
