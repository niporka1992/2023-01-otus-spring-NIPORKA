package ru.otus.spring.repositories.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.entities.Book;
import ru.otus.spring.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private final EntityManager em;

    @Override
    public Book insert(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            return book;
        }
        return em.merge(book);
    }

    @Override
    public boolean updateById(long id, Book book) {
        var query = em.createQuery(
                "update Book b set b.name= :name, b.author= :author, b.genre= :genre where b.id= :id");
        query.setParameter("id", id);
        query.setParameter("name", book.getName());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        int result = query.executeUpdate();
        return result == 1;
    }


    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-genre-entity-graph");

        var resultList = em.createQuery(
                "SELECT b FROM Book b join fetch comments", Book.class);
        resultList.setHint("javax.persistence.fetchgraph", entityGraph);
        return resultList.getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        var query = em.createQuery("delete from Book b where b.id= :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        return result == 1;
    }
}
