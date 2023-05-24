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
    public void updateById(long id, Book book) {
        Book currentBook = getById(id).get();
        em.detach(currentBook);
        currentBook.setName(book.getName());
        currentBook.setAuthor(book.getAuthor());
        currentBook.setGenre(book.getGenre());
        currentBook.setComments(book.getComments());
        em.merge(currentBook);
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAllWithAuthorAndGenre() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-author-genre-graph");

        var resultList = em.createQuery(
                "SELECT b FROM Book b", Book.class);
        resultList.setHint("javax.persistence.fetchgraph", entityGraph);
        return resultList.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Book book = new Book(id);
        em.remove(em.contains(book) ? book : em.merge(book));
    }
}
