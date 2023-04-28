package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Genre;
import ru.otus.spring.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> insert(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            return Optional.of(genre);
        }
        return Optional.of(em.merge(genre));
    }

    @Override
    public boolean updateById(long id, String name) {
        var query = em.createQuery("update Genre g set g.name= :name where g.id= :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        return query.executeUpdate() == 1;
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
    public boolean deleteById(long id) {
        var query = em.createQuery("delete from Genre g where g.id= :id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }
}
